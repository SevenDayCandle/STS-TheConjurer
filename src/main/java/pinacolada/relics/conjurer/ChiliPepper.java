package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class ChiliPepper extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ChiliPepper.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT);

    public ChiliPepper() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onTurnStart(), PMultiSkill.join(PMove.applyToRandom(2, IgnisPower.DATA))));
    }
}