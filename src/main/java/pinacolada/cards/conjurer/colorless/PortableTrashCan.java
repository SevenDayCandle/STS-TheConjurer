package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.base.moves.PMove_RemoveCard;

@VisibleCard
public class PortableTrashCan extends PCLCard {
    public static final PCLCardData DATA = register(PortableTrashCan.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.None)
            .setTags(PCLCardTag.Fleeting.make(), PCLCardTag.Retain.make(0, -1))
            .setColorless();

    public PortableTrashCan() {
        super(DATA);
    }

    @Override
    public void setup(Object input) {
        addUseMove(new PMove_RemoveCard(0, PCLCardGroupHelper.Hand));
    }
}