package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Klee extends PCLCard
{
    public static final PCLCardData DATA = register(Klee.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.RandomEnemy)
            .setDamage(1, 0)
            .setPriority(1)
            .setHp(3, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Klee()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SMALL_EXPLOSION);
        addUseMove(PCond.cooldown(2).setUpgrade(-1), PMove.obtainDrawPile(1, Klee_JumpyDumpty.DATA));
    }
}
