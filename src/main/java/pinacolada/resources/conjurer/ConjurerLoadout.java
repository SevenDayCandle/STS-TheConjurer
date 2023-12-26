package pinacolada.resources.conjurer;

import extendedui.EUIUtils;
import pinacolada.relics.conjurer.DisguisePropBox;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.relics.conjurer.QuadraticGlobe;
import pinacolada.resources.PGR;
import pinacolada.resources.loadout.PCLLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

import java.util.ArrayList;

public class ConjurerLoadout extends PCLLoadout {
    public ConjurerLoadout() {
        this(createID(ConjurerLoadout.class), -1);
    }

    public ConjurerLoadout(String id, int unlockLevel) {
        super(ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel, MAX_VALUE, MIN_CARDS, MAX_LIMIT);
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

    public ArrayList<String> getAvailableRelicIDs() {
        ArrayList<String> list = super.getAvailableRelicIDs();
        list.add(DisguisePropBox.DATA.ID);
        return list;
    }

    @Override
    public ArrayList<String> getBaseStartingRelics() {
        return EUIUtils.arrayList(PeriodicTable.DATA.ID);
    }
}
