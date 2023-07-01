package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import extendedui.utilities.ColoredString;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.listeners.OnElementalDebuffListener;
import pinacolada.interfaces.markers.StablizingPower;
import pinacolada.misc.AffinityReactions;
import pinacolada.powers.PCLPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.HashMap;
import java.util.Set;

public abstract class AbstractPCLElementalPower extends PCLPower implements StablizingPower {
    public static final int BASE_DAMAGE_MULTIPLIER = 30;
    public static final int DEFAULT_COMBUST_INCREASE = BASE_DAMAGE_MULTIPLIER / 2;
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, AbstractPCLElementalPower.class);
    public static final HashMap<String, PCLAffinity> AFFINITIES = new HashMap<>();
    public static final HashMap<String, Integer> MULTIPLIERS = new HashMap<>();
    public int stabilizeTurns;

    public AbstractPCLElementalPower(AbstractCreature owner, AbstractCreature source, String id, int amount) {
        super(owner, id);

        this.source = source;
        this.priority = 4;
        initialize(amount, PowerType.DEBUFF, false);
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
        return (getIntensifyMultiplierForLevel(powerID, level) + CombatManager.getEffectBonus(powerID)) * modifier;
    }

    public static float getIntensifyMultiplier(String powerID, int level) {
        return getIntensifyMultiplier(powerID, level, 1);
    }

    public static float getIntensifyMultiplier(String powerID, float modifier) {
        return getIntensifyMultiplier(powerID, CombatManager.playerSystem.getLevel(AFFINITIES.get(powerID)), modifier);
    }

    public static float getIntensifyMultiplierForLevel(String powerID, int level) {
        float base = MULTIPLIERS.getOrDefault(powerID, 10);
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
        return new ColoredString((int) getIntensifyMultiplier(), Color.RED, c.a);
    }

    @Override
    public String getUpdatedDescription() {
        String sub = getUpdatedDescriptionImpl();
        if (PGR.isLoaded()) {
            final PowerStrings strings = PGR.getPowerStrings(POWER_ID);
            Set<PCLAffinity> affs = ConjurerReactionMeter.meter.getElementButton(getAffinity()).getReactAffinities();
            return EUIUtils.joinStrings(" ",
                    EUIUtils.format(strings.DESCRIPTIONS[0]
                            , getIntensifyMultiplier()
                            , PCLCoreStrings.joinWithAnd(EUIUtils.map(affs, a -> a.getTooltip().getTitleOrIcon()))),
                    sub,
                    stabilizeTurns > 0 ? EUIUtils.format(strings.DESCRIPTIONS[2], stabilizeTurns + 1) : strings.DESCRIPTIONS[1]
            );
        }
        return sub;
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
    }

    protected String getUpdatedDescriptionImpl() {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getIntensifyMultiplier()));
    }

    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        flash();
    }

    public void stabilize(int turns) {
        stabilizeTurns += turns;
        flash();
    }

    public abstract AbstractGameAction.AttackEffect getAttackEffect();
}
