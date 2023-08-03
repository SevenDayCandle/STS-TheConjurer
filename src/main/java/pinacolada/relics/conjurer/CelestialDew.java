package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class CelestialDew extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(CelestialDew.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.SOLID);

    public CelestialDew() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.checkPowerSelf(1).edit(f -> f.setDebuff(true)), PMove.gain(1, PCLPowerHelper.Warding)));
    }
}