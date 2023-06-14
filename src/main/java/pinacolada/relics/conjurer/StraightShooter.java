package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.conjurer.series.genshinimpact.Klee_JumpyDumpty;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class StraightShooter extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(StraightShooter.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.CLINK);

    public StraightShooter() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.shuffle(), PMove.createDrawPile(1, Klee_JumpyDumpty.DATA.ID)));
    }
}