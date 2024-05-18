package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class PoisonMothFlight extends PCLCard {
    public static final PCLCardData DATA = register(PoisonMothFlight.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public PoisonMothFlight() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.payPower(PCLCardTarget.Single, 5, PCLPowerData.Poison), PMove.gain(15, FlowPower.DATA).setUpgrade(5), PMove.applyToSingle(5, PCLPowerData.Poison).setUpgrade(3));
    }
}
