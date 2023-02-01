package pinacolada.cards.conjurer.status;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Status_Slimed extends PCLCard
{
    public static final PCLCardData DATA = register(Status_Slimed.class, ConjurerResources.conjurer)
            .setStatus(1, CardRarity.COMMON, PCLCardTarget.None)
            .setTags(PCLCardTag.Exhaust);

    public Status_Slimed()
    {
        super(DATA);
    }
}