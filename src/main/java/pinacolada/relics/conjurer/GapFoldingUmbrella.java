package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class GapFoldingUmbrella extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(GapFoldingUmbrella.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public GapFoldingUmbrella() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(CCond.checkLevel(1), PMove.gainTemporary(3, PCLPowerData.Dexterity)));
    }
}