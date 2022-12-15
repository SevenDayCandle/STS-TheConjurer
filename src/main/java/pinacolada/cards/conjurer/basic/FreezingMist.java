package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class FreezingMist extends PCLCard
{
    public static final PCLCardData DATA = register(FreezingMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public FreezingMist()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Chilled, PCLPowerHelper.Weak).setUpgrade(1));
    }
}
