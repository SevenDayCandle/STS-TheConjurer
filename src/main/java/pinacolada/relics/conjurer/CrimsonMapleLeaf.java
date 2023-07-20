package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class CrimsonMapleLeaf extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(CrimsonMapleLeaf.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL);

    public CrimsonMapleLeaf() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, PCond.onDiscard(), CMove.gainMatter(7)));
    }
}