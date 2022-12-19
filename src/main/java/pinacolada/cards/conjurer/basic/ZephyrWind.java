package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMove;

public class ZephyrWind extends PCLCard
{
    public static final PCLCardData DATA = register(ZephyrWind.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public ZephyrWind()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.draw(2).setUpgrade(1));
        addUseMove(CMove.applyElementToSingle(3, PCLAffinity.Green).setUpgrade(1));
    }
}
