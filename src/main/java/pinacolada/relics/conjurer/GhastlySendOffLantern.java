package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class GhastlySendOffLantern extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(GhastlySendOffLantern.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public GhastlySendOffLantern() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(2, PCond.onExhaust(), PMove.applyToEnemies(1, PCLPowerData.Blinded)));
    }
}