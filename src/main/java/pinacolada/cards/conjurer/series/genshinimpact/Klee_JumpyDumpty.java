package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Klee_JumpyDumpty extends PCLCard
{
    public static final PCLCardData DATA = register(Klee_JumpyDumpty.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.SPECIAL, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(10, 4)
            .setTags(PCLCardTag.Autoplay)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Klee_JumpyDumpty()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addUseMove(PMove.applyToEveryone(3, PCLElementHelper.Ignis));
    }
}
