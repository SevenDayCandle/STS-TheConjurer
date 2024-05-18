package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_React;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class LordsAid extends PCLCard {
    public static final PCLCardData DATA = register(LordsAid.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setCostUpgrades(-1)
            .setAffinities(1, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public LordsAid() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.whenEveryTimesCombat(3, new PCond_React(), PMove.gainTemporary(1, PCLPowerData.Artifact)));
    }
}
