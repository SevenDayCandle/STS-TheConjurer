package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class ViridescentArrowFeather extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ViridescentArrowFeather.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL);

    public ViridescentArrowFeather() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, PCond.onWithdraw(), PMove.draw(1)));
    }
}