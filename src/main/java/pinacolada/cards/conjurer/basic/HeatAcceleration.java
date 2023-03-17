package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class HeatAcceleration extends PCLCard
{
    public static final PCLCardData DATA = register(HeatAcceleration.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public HeatAcceleration()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.triggerAlly(PCLCardTarget.AllAlly, 2).setUpgrade(1));
        addUseMove(PMove.apply(PCLCardTarget.Team, 1, PCLElementHelper.Vulnerable));
    }
}
