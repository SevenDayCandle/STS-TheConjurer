package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class BriarsOfPunishment extends PCLCard {
    public static final PCLCardData DATA = register(BriarsOfPunishment.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BriarsOfPunishment() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.haveLostHP(), PMod.increaseOnUse(2).setUpgrade(1), PMove.dealDamageToRandom(4).setUpgrade(1)));
    }
}
