package pinacolada.cards.conjurer.status;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Status_SearingBurn extends PCLCard {
    public static final PCLCardData DATA = register(Status_SearingBurn.class, ConjurerResources.conjurer)
            .setStatus(-2, CardRarity.SPECIAL, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Ethereal, PCLCardTag.Unplayable)
            .setAffinities(PCLAffinity.Red)
            .setCore(true);

    public Status_SearingBurn() {
        super(DATA);
    }

    @Override
    public void setup(Object input) {
        addUseMove(PCond.onDraw(), PMove.apply(PCLCardTarget.All, 2, PCLElementHelper.Ignis));
    }
}