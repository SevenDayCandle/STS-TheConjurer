package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class QuadraticGlobe extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(QuadraticGlobe.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.CLINK);

    public QuadraticGlobe() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.applyToEnemies(1, IgnisPower.DATA, AquaPower.DATA, VentusPower.DATA, PetraPower.DATA));
    }
}