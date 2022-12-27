package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

public class FreezingMist extends PCLCard
{
    public static final PCLCardData DATA = register(FreezingMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public FreezingMist()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToEnemies(3, PCLElementHelper.Chilled, PCLPowerHelper.Weak));
        addUseMove(CCond.redox(), PMove.apply(PCLCardTarget.Team, 1, PCLPowerHelper.Blur));
    }
}
