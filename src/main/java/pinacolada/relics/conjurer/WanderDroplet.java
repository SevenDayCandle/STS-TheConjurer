package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class WanderDroplet extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(WanderDroplet.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL);

    public WanderDroplet() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.shuffle(), PMod.perCard(2, PCLCardGroupHelper.MasterDeck), CMove.addLevel(1, PCLAffinity.Blue)));
    }
}