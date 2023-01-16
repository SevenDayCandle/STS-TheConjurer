package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;

public class PetraPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, PetraPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Orange);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 25);

    public PetraPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public float calculateValue(int amount, float multiplier)
    {
        return Math.max(1, amount * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return AttackEffects.EARTH;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.PCL_ORB_EARTH_CHANNEL, 0.95f, 1.05f);
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions, int amount)
    {
        PCLActions.bottom.applyPower(source, PCLCardTarget.Single, PCLPowerHelper.NextTurnBlock, (int) calculateValue(amount, getIntensifyMultiplier()));
        super.onReact(source, reactions, amount);
    }
}
