package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class BoilingPoint extends PCLCard
{
    public static final PCLCardData DATA = register(BoilingPoint.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(6, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public BoilingPoint()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addBlockMove();
        addUseMove(CCond.combust(), PMove.applyToSingle(2, PCLPowerHelper.Vulnerable, PCLElementHelper.Gelus));
    }
}
