package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUI;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.utilities.ColoredString;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.*;
import pinacolada.interfaces.listeners.OnElementalDebuffListener;
import pinacolada.powers.PCLPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.skills.PSkill;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractPCLElementalPower extends PCLPower {
    private static List<PCLAffinity> hovered;
    private static AbstractCard last;
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, AbstractPCLElementalPower.class);
    protected float flashTimer;

    public AbstractPCLElementalPower(ElementPowerData data, AbstractCreature owner, AbstractCreature source, int amount) {
        super(data, owner, source, amount);
    }

    public static float getIntensifyMultiplier(ElementPowerData powerID) {
        return getIntensifyMultiplier(powerID, ConjurerReactionMeter.meter.getLevel(powerID.affinity), 1);
    }

    public static float getIntensifyMultiplier(ElementPowerData powerID, int level, float modifier) {
        return (getIntensifyMultiplierForLevel(powerID, level)) * modifier;
    }

    public static float getIntensifyMultiplier(ElementPowerData powerID, int level) {
        return getIntensifyMultiplier(powerID, level, 1);
    }

    public static float getIntensifyMultiplier(ElementPowerData powerID, float modifier) {
        return getIntensifyMultiplier(powerID, ConjurerReactionMeter.meter.getLevel(powerID.affinity), modifier);
    }

    public static float getIntensifyMultiplierForLevel(ElementPowerData powerID, int level) {
        float base = CombatManager.getEffectBonus(powerID.ID);
        float increase = level * base / 2f;
        return base + increase;
    }

    public static ElementPowerData registerElement(Class<? extends AbstractPCLElementalPower> powerClass, PCLAffinity affinity) {
        return registerPowerData(new ElementPowerData(powerClass, affinity));
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
        if (this.turns > 1) {
            this.turns = turns - 1;
        }
    }

    public PCLAffinity getAffinity() {
        return ((ElementPowerData) data).affinity;
    }

    @Override
    protected Color getImageColor(Color c) {
        return (enabled) ? c : disabledColor;
    }

    @Override
    protected ColoredString getPrimaryAmount(Color c) {
        return new ColoredString(amount, turns > 1 ? Settings.BLUE_TEXT_COLOR : Color.WHITE, c.a);
    }

    @Override
    public String getUpdatedDescription() {
        String base = EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterBonus, PGR.core.strings.subjects_this);
        if (CombatManager.inBattle()) {
            return EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, base, ConjurerReactionMeter.meter.getElementButton(getAffinity()).getEffectsString());
        }
        return super.getUpdatedDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
    }

    public void onReact(PCLUseInfo info, AffinityReactions reactions) {
        ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(getAffinity());
        HashMap<PCLAffinity, Integer> values = reactions.getReactants(getAffinity(), owner);
        if (values != null && !values.isEmpty()) {
            for (Map.Entry<PCLAffinity, Integer> entry : values.entrySet()) {
                for (PSkill<?> skill : button.getReactEffects(entry.getKey())) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        skill.use(info, PCLActions.bottom);
                    }
                }
            }
            flash();
            if (turns <= 1) {
                reducePower(1);
            }
        }
    }

    @Override
    protected void renderIconsImpl(SpriteBatch sb, float x, float y, Color borderColor, Color imageColor) {
        PCLRenderHelpers.drawCentered(sb, imageColor, this.img, x, y, 32.0F, 32.0F, 1f, 0.0F);
        if (flashTimer > 0) {
            Color flashColor = imageColor.cpy();
            flashColor.a = 0.5f + MathUtils.sin(flashTimer) * 0.5f;
            EUIRenderHelpers.drawBlendedWithShader(sb, EUIRenderHelpers.BlendingMode.Glowing, EUIRenderHelpers.ShaderMode.Colorize, s -> {
                PCLRenderHelpers.drawCentered(s, flashColor, this.img, x, y, 32.0F, 32.0F, 1f, 0.0F);
            });
        }
    }

    @Override
    public void updateHitbox() {
        super.updateHitbox();
        AbstractCard card = ConjurerReactionMeter.meter.getLastCard();
        if (card != null) {
            if (last != card) {
                last = card;
                hovered = GameUtilities.getVisiblePCLAffinities(card);
            }
            if (hovered != null) {
                ConjurerElementButton button = ConjurerReactionMeter.meter.getElementButton(getAffinity());
                if (button != null) {
                    for (PCLAffinity affinity : hovered) {
                        if (button.hasReact(affinity)) {
                            flashTimer += EUI.delta() * 4;
                            return;
                        }
                    }
                }
            }
        }
        flashTimer = 0;
    }

    public abstract AbstractGameAction.AttackEffect getAttackEffect();
}
