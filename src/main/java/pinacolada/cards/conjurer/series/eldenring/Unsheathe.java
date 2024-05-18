package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Unsheathe extends PCLCard {
    public static final PCLCardData DATA = register(Unsheathe.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(9, 3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Unsheathe() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.fetch(2, PCLCardGroupHelper.DiscardPile), PMove.retain(1).useParentForce());
    }
}
