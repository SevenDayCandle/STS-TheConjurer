package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

public class OracleBubbles extends PCLCard
{
    public static final PCLCardData DATA = register(OracleBubbles.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setBlock(3, 0)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public OracleBubbles()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.scryPer(3).setUpgrade(1).setAffinity(PCLAffinity.Blue), PMove.applyToSingle(2, PCLPowerHelper.Weak, PCLPowerHelper.Chilled));
    }
}
