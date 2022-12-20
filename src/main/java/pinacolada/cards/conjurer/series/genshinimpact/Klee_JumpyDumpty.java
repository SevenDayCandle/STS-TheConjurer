package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;

public class Klee_JumpyDumpty extends PCLCard
{
    public static final PCLCardData DATA = register(Klee_JumpyDumpty.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.SPECIAL, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(10, 4)
            .setTags(PCLCardTag.Autoplay, PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Klee_JumpyDumpty()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SMALL_EXPLOSION);
    }
}
