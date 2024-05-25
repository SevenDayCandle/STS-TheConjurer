package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Quickstep extends PCLCard {
    public static final PCLCardData DATA = register(Quickstep.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setAffinities(1, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Quickstep() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.draw(2).setUpgrade(1));
        addUseMove(PMove.cycle(1));
    }
}
