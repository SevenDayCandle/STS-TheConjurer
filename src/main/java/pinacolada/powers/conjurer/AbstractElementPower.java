package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.EUI;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.powers.PCLPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.List;

public abstract class AbstractElementPower extends PCLPower {
    private static List<PCLAffinity> hovered;
    private static AbstractCard last;
    protected float flashTimer;

    public AbstractElementPower(ElementPowerData data, AbstractCreature owner, AbstractCreature source, int amount) {
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

    public static ElementPowerData registerElement(Class<? extends AbstractElementPower> powerClass, PCLAffinity affinity) {
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
    public String getUpdatedDescription() {
        if (CombatManager.inBattle()) {
            String base = EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterBonus, PGR.core.strings.subjects_this);
            return EUIUtils.joinStrings(EUIUtils.SPLIT_LINE, base, ConjurerReactionMeter.meter.getElementButton(getAffinity()).getEffectsString());
        }
        return super.getUpdatedDescription();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
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
