package pinacolada.cards.conjurer.curse;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Curse_AscendersBane extends PCLCard
{
    public static final PCLCardData DATA = register(Curse_AscendersBane.class, ConjurerResources.conjurer)
            .setCurse(-2, PCLCardTarget.None, true)
            .setTags(PCLCardTag.Ethereal, PCLCardTag.Soulbound, PCLCardTag.Unplayable);

    public Curse_AscendersBane()
    {
        super(DATA);
    }
}