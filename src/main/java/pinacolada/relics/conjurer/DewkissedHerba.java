package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class DewkissedHerba extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(DewkissedHerba.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public DewkissedHerba() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onDiscard(), PMove.gain(2, FlowPower.DATA)));
    }
}