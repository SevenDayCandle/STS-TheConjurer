package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class PoisonMist extends PCLCard
{
    public static final PCLCardData DATA = register(PoisonMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public PoisonMist()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToEnemies(4, PCLPowerHelper.Poison).setUpgrade(2));
    }
}
