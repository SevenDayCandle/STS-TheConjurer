package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class PearlCage extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(PearlCage.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL);

    public PearlCage() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(2, PCond.onWithdraw(), PMove.gainTempHP(1)));
    }
}