package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.resources.conjurer.ConjurerResources;

public class GelusPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, GelusPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Blue);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 25);

    public GelusPower(AbstractCreature owner, AbstractCreature source, int amount)
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
        return AttackEffects.ICE;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ORB_FROST_CHANNEL, 0.95f, 1.05f);
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions, int amount)
    {
        PCLActions.bottom.applyPower(source, owner, new FrostbitePower(owner, (int) calculateValue(amount, getIntensifyMultiplier())));
        super.onReact(source, reactions, amount);
    }
}
