package pinacolada.resources.conjurer;

import pinacolada.resources.PGR;
import pinacolada.resources.loadout.PCLLoadout;

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
}
