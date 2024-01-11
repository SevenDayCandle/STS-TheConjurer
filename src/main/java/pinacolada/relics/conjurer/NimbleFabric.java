package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class NimbleFabric extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(NimbleFabric.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public NimbleFabric() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.gain(8, FlowPower.DATA));
    }
}