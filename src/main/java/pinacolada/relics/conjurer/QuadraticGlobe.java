package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class QuadraticGlobe extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(QuadraticGlobe.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.CLINK);

    public QuadraticGlobe() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.shuffle(), PMod.perCard(PCLCardGroupHelper.MasterDeck), CMove.gainMatter(2)));
    }
}