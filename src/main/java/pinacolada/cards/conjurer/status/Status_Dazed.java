package pinacolada.cards.conjurer.status;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Status_Dazed extends PCLCard
{
    public static final PCLCardData DATA = register(Status_Dazed.class, ConjurerResources.conjurer)
            .setStatus(-2, CardRarity.COMMON, PCLCardTarget.None)
            .setTags(PCLCardTag.Ethereal, PCLCardTag.Unplayable);

    public Status_Dazed()
    {
        super(DATA);
    }

}