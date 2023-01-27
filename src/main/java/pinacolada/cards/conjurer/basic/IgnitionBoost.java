package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class IgnitionBoost extends PCLCard
{
    public static final PCLCardData DATA = register(IgnitionBoost.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public IgnitionBoost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.triggerAlly(PCLCardTarget.AllAlly, 2));
        addUseMove(PMove.apply(PCLCardTarget.Team, 1, PCLElementHelper.Vulnerable));
    }
}
