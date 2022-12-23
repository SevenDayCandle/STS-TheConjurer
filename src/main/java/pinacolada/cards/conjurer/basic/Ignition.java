package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class Ignition extends PCLCard
{
    public static final PCLCardData DATA = register(Ignition.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public Ignition()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.triggerAlly(PCLCardTarget.AllAlly, 2));
        addUseMove(PMove.apply(PCLCardTarget.Team, 3, PCLElementHelper.Burned));
    }
}
