package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class Klee_JumpyDumpty extends PCLCard
{
    public static final PCLCardData DATA = register(Klee_JumpyDumpty.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.SPECIAL, PCLAttackType.Magical, PCLCardTarget.RandomEnemy)
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
        addUseMove(PMove.applyToRandom(3, PCLPowerHelper.Blasted));
        addUseMove(PCond.combust(), PMultiSkill.join(PMove.gain(1, PCLPowerHelper.Blasted), PMove.obtainDrawPile(1, Klee_JumpyDumpty.DATA)));
    }
}
