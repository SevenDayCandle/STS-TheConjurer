package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class SuppressingEdict extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SuppressingEdict.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.honkai);

    public SuppressingEdict() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PCond.cycle(1), PMove.applyToEveryone(1, PCLPowerData.Silenced));
    }
}