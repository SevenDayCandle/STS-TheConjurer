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
public class Bloodrose extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(Bloodrose.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Bloodrose() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, PCond.haveLostHP(), PMove.applyToEnemies(2, PCLPowerData.Bruised)));
    }
}