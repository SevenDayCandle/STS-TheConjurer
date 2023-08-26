package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class PeriodicTable extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.SOLID);

    public PeriodicTable() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.applyToEnemies(1, PCLElementHelper.Ignis, PCLElementHelper.Aqua, PCLElementHelper.Ventus, PCLElementHelper.Petra));
    }
}