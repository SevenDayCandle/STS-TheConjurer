package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class SundialOfEnduringJade extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SundialOfEnduringJade.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public SundialOfEnduringJade() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.gainTemporary(10, PCLPowerData.Resistance));
    }
}