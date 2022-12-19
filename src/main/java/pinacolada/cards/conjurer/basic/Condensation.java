package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

public class Condensation extends PCLCard
{
    public static final PCLCardData DATA = register(Condensation.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setBlock(2, 2)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public Condensation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(CMove.applyElementToSingle(3, PCLAffinity.Blue).setUpgrade(1));
    }
}
