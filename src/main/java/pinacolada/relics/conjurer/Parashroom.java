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
public class Parashroom extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(Parashroom.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Parashroom() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.applyToEnemies(2, PCLPowerData.Shackles));
    }
}