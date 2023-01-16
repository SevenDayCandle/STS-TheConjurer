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
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.interfaces.listeners.OnElementalDebuffListener;
import pinacolada.interfaces.markers.MultiplicativePower;
import pinacolada.interfaces.markers.StablizingPower;
import pinacolada.misc.CombatManager;
import pinacolada.powers.PCLPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.HashMap;
import java.util.Set;

public abstract class AbstractPCLElementalPower extends PCLPower implements MultiplicativePower, StablizingPower
{
    public static final int BASE_DAMAGE_MULTIPLIER = 40;
    public static final int DEFAULT_COMBUST_INCREASE = BASE_DAMAGE_MULTIPLIER / 2;
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, AbstractPCLElementalPower.class);
    public static final HashMap<String, PCLAffinity> AFFINITIES = new HashMap<>();
    public static final HashMap<String, Integer> MULTIPLIERS = new HashMap<>();
    public static final HashMap<String, Integer> DENOMINATORS = new HashMap<>();
    public int stabilizeTurns;

    public AbstractPCLElementalPower(AbstractCreature owner, AbstractCreature source, String id, int amount)
    {
        super(owner, id);

        this.source = source;
        this.priority = 4;
        initialize(amount, PowerType.DEBUFF, false);
    }

    public static float getAmplifyMultiplier(PCLAffinity affinity)
    {
        return getAmplifyMultiplier(affinity, CombatManager.playerSystem.getLevel(affinity),1);
    }

    public static float getAmplifyMultiplier(PCLAffinity affinity, float modifier)
    {
        return getAmplifyMultiplier(affinity, CombatManager.playerSystem.getLevel(affinity), modifier);
    }

    public static float getAmplifyMultiplier(PCLAffinity affinity, int level)
    {
        return getAmplifyMultiplier(affinity, level, 1);
    }

    public static float getAmplifyMultiplier(PCLAffinity affinity, int level, float modifier)
    {
        return (getAmplifyMultiplierForLevel(level)) * modifier;
    }

    public static float getAmplifyMultiplierForLevel(int level) {
        return BASE_DAMAGE_MULTIPLIER + DEFAULT_COMBUST_INCREASE * level;
    }

    public static float getIntensifyMultiplier(String powerID)
    {
        return getIntensifyMultiplier(powerID, CombatManager.playerSystem.getLevel(AFFINITIES.get(powerID)),1);
    }

    public static float getIntensifyMultiplier(String powerID, float modifier)
    {
        return getIntensifyMultiplier(powerID, CombatManager.playerSystem.getLevel(AFFINITIES.get(powerID)), modifier);
    }

    public static float getIntensifyMultiplier(String powerID, int level)
    {
        return getIntensifyMultiplier(powerID, level, 1);
    }

    public static float getIntensifyMultiplier(String powerID, int level, float modifier)
    {
        return (getIntensifyMultiplierForLevel(powerID, level) + CombatManager.getEffectBonus(powerID)) * modifier;
    }

    public static float getIntensifyMultiplierForLevel(String powerID, int level) {
        float base = MULTIPLIERS.getOrDefault(powerID, 10);
        float increase = level * base / 2f;
        if (DENOMINATORS.containsKey(powerID)) {
            float top = DENOMINATORS.get(powerID);
            return 100 * ((base + increase) / (top + base + increase));
        }
        return base + increase;
    }

    public static PCLAffinity setAffinity(String id, PCLAffinity affinity)
    {
        AFFINITIES.put(id, affinity);
        return affinity;
    }

    public static int setTop(String id, int multiplier)
    {
        DENOMINATORS.put(id, multiplier);
        return multiplier;
    }

    public static int setMultiplier(String id, int multiplier)
    {
        MULTIPLIERS.put(id, multiplier);
        return multiplier;
    }

    public PCLAffinity getAffinity()
    {
        return AFFINITIES.get(ID);
    }

    protected float getElementalMultiplier()
    {
        float mult = 1;
        if (owner != null && owner.powers != null)
        {
            for (AbstractPower po : owner.powers)
            {
                if (po instanceof OnElementalDebuffListener)
                {
                    mult = ((OnElementalDebuffListener) po).getPercentage(mult, this, owner);
                }
            }
        }
        return mult;
    }

    public float getIntensifyMultiplier()
    {
        if (GameUtilities.isPlayer(owner))
        {
            return MULTIPLIERS.get(ID);
        }
        return getIntensifyMultiplier(ID, getElementalMultiplier());
    }

    public float calculateValue(int amount, float multiplier)
    {
        return amount + MathUtils.ceil(amount * (multiplier / 100f));
    }

    @Override
    protected ColoredString getPrimaryAmount(Color c)
    {
        if (stabilizeTurns > 0 && this.amount > 0)
        {
            return new ColoredString(amount, Settings.BLUE_TEXT_COLOR, c.a);
        }
        return super.getPrimaryAmount(c);
    }

    @Override
    protected ColoredString getSecondaryAmount(Color c)
    {
        return ConjurerReactionMeter.meter.isHighlighted() ? new ColoredString((int) calculateValue(ConjurerReactionMeter.meter.getPreviewGain(), getIntensifyMultiplier()), Color.GREEN, c.a) : new ColoredString((int) getIntensifyMultiplier(), Color.RED, c.a);
    }

    @Override
    public String getUpdatedDescription()
    {
        String sub = getUpdatedDescriptionImpl();
        if (PGR.isLoaded())
        {
            final PowerStrings strings = PGR.getPowerStrings(POWER_ID);
            Set<PCLAffinity> affs = ConjurerReactionMeter.meter.getElementButton(getAffinity()).getCombustAffinities();
            Set<PCLAffinity> rAffs = ConjurerReactionMeter.meter.getElementButton(getAffinity()).getRedoxAffinities();
            return EUIUtils.joinStrings(" ",
                    EUIUtils.format(strings.DESCRIPTIONS[0]
                            , getIntensifyMultiplier()
                            , PCLCoreStrings.joinWithAnd(EUIUtils.map(affs, a -> a.getTooltip().getTitleOrIcon()))
                            , PCLCoreStrings.joinWithAnd(EUIUtils.map(rAffs, a -> a.getTooltip().getTitleOrIcon()))),
                    sub,
                    stabilizeTurns > 0 ? EUIUtils.format(strings.DESCRIPTIONS[2], stabilizeTurns + 1) : strings.DESCRIPTIONS[1]
            );
        }
        return sub;
    }

    @Override
    public void onInitialApplication()
    {
        super.onInitialApplication();
    }

    public void stabilize(int turns)
    {
        stabilizeTurns += turns;
        flash();
    }

    protected String getUpdatedDescriptionImpl()
    {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getIntensifyMultiplier()));
    }

    @Override
    public void atEndOfRound()
    {
        super.atEndOfRound();
        if (stabilizeTurns > 0) {
            stabilizeTurns -= 1;
        }
        else {
            removePower();
        }
    }

    public void onReact(AbstractCreature source, AffinityReactions reactions, int amount)
    {
        flash();
    }

    public abstract AbstractGameAction.AttackEffect getAttackEffect();
}
