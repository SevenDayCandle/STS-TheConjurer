package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMove;

public class FreezingMist extends PCLCard
{
    public static final PCLCardData DATA = register(FreezingMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public FreezingMist()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(CMove.applyElementToEnemies(3, PCLAffinity.Blue));
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Weak));
    }
}
