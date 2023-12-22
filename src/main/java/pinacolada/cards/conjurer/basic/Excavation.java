package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Excavation extends PCLCard {
    public static final PCLCardData DATA = register(Excavation.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(11, 3)
            .setAffinities(PCLAffinity.Orange)
            .setCore();

    public Excavation() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.fetch(1, PCLCardGroupHelper.DiscardPile), PMove.retain(1).useParentForce());
    }
}
