package pinacolada.resources.conjurer;

import pinacolada.relics.conjurer.DisguisePropBox;
import pinacolada.relics.conjurer.QuadraticGlobe;
import pinacolada.resources.PGR;
import pinacolada.resources.loadout.LoadoutRelicSlot;
import pinacolada.resources.loadout.PCLLoadout;

public class ConjurerLoadout extends PCLLoadout {

    public ConjurerLoadout() {
        this(createID(ConjurerLoadout.class), -1);
    }

    public ConjurerLoadout(String id, int unlockLevel) {
        super(ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel);
    }

    public ConjurerLoadout(String id) {
        this(id, 0);
    }

    public static String createID(Class<? extends PCLLoadout> type) {
        return createID(ConjurerResources.ID, type);
    }

    public static ConjurerLoadout generate(String prefix) {
        return generate(prefix, 0);
    }

    public static ConjurerLoadout generate(String prefix, int unlockLevel) {
        return register(new ConjurerLoadout(PGR.createID(ConjurerResources.ID, prefix), unlockLevel));
    }

    public void addLoadoutRelics(LoadoutRelicSlot r1) {
        super.addLoadoutRelics(r1);
        r1.addItem(new QuadraticGlobe(), 12);
        r1.addItem(new DisguisePropBox(), 12);
    }
}
