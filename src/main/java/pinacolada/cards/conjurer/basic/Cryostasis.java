package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class Cryostasis extends PCLCard
{
    public static final PCLCardData DATA = register(Cryostasis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(12, 0)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public Cryostasis()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.stabilize(PCLCardTarget.Single, PCLElementHelper.Burned, PCLElementHelper.Chilled));
    }
}
