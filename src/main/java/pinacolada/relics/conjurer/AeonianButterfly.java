package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class AeonianButterfly extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(AeonianButterfly.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public AeonianButterfly() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.checkPower(PCLCardTarget.Any, 1, PCLPowerData.Vulnerable), PMove.applyToRandom(1, ElementPowerData.Poison)));
    }
}