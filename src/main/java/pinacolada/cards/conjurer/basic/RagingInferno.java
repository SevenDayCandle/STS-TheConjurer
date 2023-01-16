package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class RagingInferno extends PCLCard
{
    public static final PCLCardData DATA = register(RagingInferno.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Ranged)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(35, 5)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public RagingInferno()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove();
        addUseMove(PMove.applyToSingle(8, PCLElementHelper.Ignis).setUpgrade(3));
    }
}
