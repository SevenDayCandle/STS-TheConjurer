package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class Firebomb extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(Firebomb.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Firebomb() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.applyToEnemies(2, IgnisPower.DATA));
    }
}