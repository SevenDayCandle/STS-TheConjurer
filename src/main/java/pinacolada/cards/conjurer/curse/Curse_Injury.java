package pinacolada.cards.conjurer.curse;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Curse_Injury extends PCLCard
{
    public static final PCLCardData DATA = register(Curse_Injury.class, ConjurerResources.conjurer)
            .setCurse(-2, PCLCardTarget.None, false, true)
            .setTags(PCLCardTag.Unplayable)
            .setAffinities(PCLAffinity.Purple);

    public Curse_Injury()
    {
        super(DATA);
    }
}