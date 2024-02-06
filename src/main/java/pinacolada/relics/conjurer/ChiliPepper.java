package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class ChiliPepper extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ChiliPepper.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT);

    public ChiliPepper() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.applyToEnemies(2, IgnisPower.DATA));
    }
}