package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class RimeBlueMossClump extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(RimeBlueMossClump.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public RimeBlueMossClump() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.applyToEnemies(3, CooledPower.DATA));
    }
}