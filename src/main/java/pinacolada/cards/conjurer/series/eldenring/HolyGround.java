package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class HolyGround extends PCLCard {
    public static final PCLCardData DATA = register(HolyGround.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Yellow, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public HolyGround() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onFetch(), PMove.gainTempHP(2).setUpgrade(1)));
    }
}
