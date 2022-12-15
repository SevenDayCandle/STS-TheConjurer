package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

public class GreatOracularBubble extends PCLCard
{
    public static final PCLCardData DATA = register(GreatOracularBubble.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE)
            .setBlock(10, 2)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public GreatOracularBubble()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.scryPer(7).setUpgrade(1).setAffinity(PCLAffinity.Blue, PCLAffinity.Green), PMove.applyToSingle(1, PCLPowerHelper.Weak));
    }
}
