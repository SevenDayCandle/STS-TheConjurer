package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;
import pinacolada.skills.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_OnRetain;
import pinacolada.skills.skills.base.conditions.PCond_OnUpgrade;

@VisibleCard
public class Geomorphology extends PCLCard {
    public static final PCLCardData DATA = register(Geomorphology.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public Geomorphology() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PMultiCond.or(new PCond_OnRetain(), new PCond_OnUpgrade()), PMove.gainBlock(3).setUpgrade(1)));
    }
}
