package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Keystone extends PCLCard {
    public static final PCLCardData DATA = register(Keystone.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.SingleAlly)
            .setBlock(8, 3)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public Keystone() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.reshuffle(2, PCLCardGroupHelper.DiscardPile), PMove.modifyAffinity(1, 1, PCLAffinity.Orange).useParentForce());
    }
}
