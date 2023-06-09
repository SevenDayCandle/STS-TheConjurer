package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Paleontology extends PCLCard {
    public static final PCLCardData DATA = register(Paleontology.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Orange)
            .setCore();

    public Paleontology() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onTurnStart(), PMove.fetch(2, 1, PCLCardGroupHelper.DiscardPile).setUpgrade(1)));
    }
}
