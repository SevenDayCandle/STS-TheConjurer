package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PShift;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class ComfortFood extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ComfortFood.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.CLINK);

    public ComfortFood() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PShift.obtain(), PMove.heal(12));
        addUseMove(new PRoot(), PMove.gainTempHP(3));
    }
}