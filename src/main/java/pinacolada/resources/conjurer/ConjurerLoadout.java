package pinacolada.resources.conjurer;

import pinacolada.relics.conjurer.DisguisePropBox;
import pinacolada.relics.conjurer.QuadraticGlobe;
import pinacolada.resources.PGR;
import pinacolada.resources.loadout.PCLLoadout;
import pinacolada.resources.loadout.PCLRelicSlot;

public class ConjurerLoadout extends PCLLoadout {

    public static ConjurerLoadout generate(String prefix) {
        return new ConjurerLoadout(PGR.createID(ConjurerResources.ID, prefix));
    }

    public static String createID(Class<? extends PCLLoadout> type) {
        return createID(ConjurerResources.ID, type);
    }

    public ConjurerLoadout() {
        this(createID(ConjurerLoadout.class), -1);
    }

    public ConjurerLoadout(String id, int unlockLevel) {
        super(ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel);
    }

    public ConjurerLoadout(String id) {
        this(id, 0);
    }

    public void addLoadoutRelics(PCLRelicSlot r1) {
        super.addLoadoutRelics(r1);
        r1.addItem(new QuadraticGlobe(), 5);
        r1.addItem(new DisguisePropBox(), 20);
    }
}
