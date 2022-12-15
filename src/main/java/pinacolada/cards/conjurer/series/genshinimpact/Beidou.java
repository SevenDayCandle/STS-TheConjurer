package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_IsAttacking;

public class Beidou extends PCLCard
{
    public static final PCLCardData DATA = register(Beidou.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.COMMON, PCLAttackType.Normal)
            .setDamage(2, 0)
            .setBlock(15, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Beidou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY);
        addUseMove(PCond.ifElse(PMove.gain(4, PCLPowerHelper.CounterAttack), PMove.gain(5, PCLPowerHelper.NextTurnBlock), new PCond_IsAttacking(PCLCardTarget.Single)));
    }
}
