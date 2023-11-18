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
public class BloodthirstyYingYangOrb extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(BloodthirstyYingYangOrb.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public BloodthirstyYingYangOrb() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.haveLostHP(9), PMove.gain(1, PCLPowerData.Energized)));
    }
}