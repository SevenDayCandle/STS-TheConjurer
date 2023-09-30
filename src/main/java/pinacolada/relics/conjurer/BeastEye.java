package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class BeastEye extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(BeastEye.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BeastEye() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, PCond.fatalMinion(), PMove.gain(2, PCLPowerData.Strength)));
    }
}