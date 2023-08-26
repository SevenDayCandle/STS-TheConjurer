package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
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
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.interfaces.listeners.OnElementalDebuffListener;
import pinacolada.interfaces.markers.StablizingPower;
import pinacolada.powers.PCLPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class AbstractPCLElementalPower extends PCLPower implements StablizingPower {
    private static List<PCLAffinity> hovered;
    private static AbstractCard last;
    public static final int BASE_DAMAGE_MULTIPLIER = 40;
    public static final int DEFAULT_COMBUST_INCREASE = BASE_DAMAGE_MULTIPLIER / 2;
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, AbstractPCLElementalPower.class);
    public static final HashMap<String, PCLAffinity> AFFINITIES = new HashMap<>();
    public static final HashMap<String, Integer> MULTIPLIERS = new HashMap<>();
    public int stabilizeTurns;
    protected float flashTimer;

    public AbstractPCLElementalPower(AbstractCreature owner, AbstractCreature source, String id, int amount) {
        super(owner, id);

        this.source = source;
        this.priority = 4;
        initialize(amount, NeutralPowertypePatch.NEUTRAL, false);
    }

    public static float getAmplifyMultiplier(PCLAffinity affinity) {
        return getAmplifyMultiplier(CombatManager.playerSystem.getLevel(affinity), ConjurerReactionMeter.meter.getAmplifyOffset(affinity));
    }

    public static float getAmplifyMultiplier(int level, int modifier) {
        return (BASE_DAMAGE_MULTIPLIER + modifier + (DEFAULT_COMBUST_INCREASE + modifier / 2f) * level);
    }

    public static float getIntensifyMultiplier(String powerID) {
        return getIntensifyMultiplier(powerID, CombatManager.playerSystem.getLevel(AFFINITIES.get(powerID)), 1);
    }

    public static float getIntensifyMultiplier(String powerID, int level, float modifier) {
        return (getIntensifyMultiplierForLevel(powerID, level)) * modifier;
    }

    public static float getIntensifyMultiplier(String powerID, int level) {
        return getIntensifyMultiplier(powerID, level, 1);
    }

    public static float getIntensifyMultiplier(String powerID, float modifier) {
        return getIntensifyMultiplier(powerID, CombatManager.playerSystem.getLevel(AFFINITIES.get(powerID)), modifier);
    }

    public static float getIntensifyMultiplierForLevel(String powerID, int level) {
        float base = MULTIPLIERS.getOrDefault(powerID, 100) + CombatManager.getEffectBonus(powerID);
        float increase = level * base / 2f;
        return base + increase;
    }

    public static PCLAffinity setAffinity(String id, PCLAffinity affinity) {
        AFFINITIES.put(id, affinity);
        return affinity;
    }

    public static int setMultiplier(String id, int multiplier) {
        MULTIPLIERS.put(id, multiplier);
        return multiplier;
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
        if (stabilizeTurns > 0) {
            stabilizeTurns -= 1;
        }
        else {
            removePower(PCLActions.instant);
        }
    }

    public float calculateValue(AffinityReactions reactions) {
        return calculateValue(reactions.getValue(getAffinity()), getIntensifyMultiplier());
    }

    public float calculateValue(int amount, float multiplier) {
        return amount > 0 ? MathUtils.ceil(amount * (multiplier / 100f)) : 0;
    }

    public PCLAffinity getAffinity() {
        return AFFINITIES.get(ID);
    }

    protected float getElementalMultiplier() {
        float mult = 1;
        if (owner != null && owner.powers != null) {
            for (AbstractPower po : owner.powers) {
                if (po instanceof OnElementalDebuffListener) {
                    mult = ((OnElementalDebuffListener) po).getPercentage(mult, this, owner);
                }
            }
        }
        return mult;
    }

    @Override
    protected Color getImageColor(Color c) {
        return (enabled) ? c : disabledColor;
    }

    public float getIntensifyMultiplier() {
        if (GameUtilities.isPlayer(owner)) {
            return MULTIPLIERS.get(ID);
        }
        return getIntensifyMultiplier(ID, getElementalMultiplier());
    }

    @Override
    protected ColoredString getPrimaryAmount(Color c) {
        return new ColoredString(amount, stabilizeTurns > 0 ? Settings.BLUE_TEXT_COLOR : Color.WHITE, c.a);
    }

    @Override
    protected ColoredString getSecondaryAmount(Color c) {
        if (ConjurerReactionMeter.meter.isHighlighted()) {
            AffinityReactions reactions = ConjurerReactionMeter.meter.getPreviewReactions();
            if (reactions != null) {
                return new ColoredString((int) calculateValue(reactions), Color.GREEN, c.a);
            }
        }
        return null;
    }

    @Override
    public String getUpdatedDescription() {
        String sub = getUpdatedDescriptionImpl();
        if (PGR.isLoaded()) {
            final PowerStrings strings = PGR.getPowerStrings(POWER_ID);
            Set<PCLAffinity> affs = ConjurerReactionMeter.meter.getElementButton(getAffinity()).getReactAffinities();
            return EUIUtils.joinStrings(" ",
                    EUIUtils.format(strings.DESCRIPTIONS[0]
                            , getAmplifyMultiplier(getAffinity())
                            , PCLCoreStrings.joinWithAnd(EUIUtils.map(affs, a -> a.getTooltip().getTitleOrIcon()))),
                    sub,
                    stabilizeTurns > 0 ? EUIUtils.format(strings.DESCRIPTIONS[2], stabilizeTurns + 1) : strings.DESCRIPTIONS[1]
            );
        }
        return sub;
    }

    protected String getUpdatedDescriptionImpl() {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getIntensifyMultiplier()));
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
    }

    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        flash();
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

    public void stabilize(int turns) {
        stabilizeTurns += turns;
        flash();
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
