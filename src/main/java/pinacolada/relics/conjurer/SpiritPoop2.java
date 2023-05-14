package pinacolada.relics.conjurer;

import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

public class SpiritPoop2 extends PCLRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SPECIAL, LandingSound.SOLID);

    public SpiritPoop2() {
        super(DATA);
    }
}