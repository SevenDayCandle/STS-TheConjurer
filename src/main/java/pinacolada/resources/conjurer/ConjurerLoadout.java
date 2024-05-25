package pinacolada.resources.conjurer;

import extendedui.EUIUtils;
import pinacolada.relics.conjurer.MimicsVeil;
import pinacolada.relics.conjurer.AcademyScroll;
import pinacolada.resources.PGR;
import pinacolada.resources.loadout.PCLLoadout;

import java.util.ArrayList;

public class ConjurerLoadout extends PCLLoadout {
    public ConjurerLoadout() {
        this(createID(ConjurerLoadout.class), -1);
    }

    public ConjurerLoadout(PCLLoadoutGroup group, String id, int unlockLevel) {
        super(group, ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel, MAX_VALUE, MIN_CARDS, MAX_LIMIT);
    }

    public ConjurerLoadout(String id, int unlockLevel) {
        super(ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel, MAX_VALUE, MIN_CARDS, MAX_LIMIT);
    }

    public ConjurerLoadout(PCLLoadoutGroup group, String id) {
        this(group, id, 0);
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
        list.add(MimicsVeil.DATA.ID);
        return list;
    }

    @Override
    public ArrayList<String> getBaseStartingRelics() {
        return EUIUtils.arrayList(AcademyScroll.DATA.ID);
    }
}
