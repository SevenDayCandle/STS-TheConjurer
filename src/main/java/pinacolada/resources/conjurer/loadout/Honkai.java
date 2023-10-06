package pinacolada.resources.conjurer.loadout;

import extendedui.EUIUtils;
import pinacolada.cards.conjurer.basic.DefendR;
import pinacolada.cards.conjurer.basic.PowderSnow;
import pinacolada.cards.conjurer.basic.StrikeB;
import pinacolada.cards.conjurer.series.honkai.DanHeng;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

import java.util.ArrayList;

public class Honkai extends ConjurerLoadout {
    public static final String ID = createID(Honkai.class);

    public Honkai() {
        super(ID, 6);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeB.DATA.ID, 4);
        data.addCardSlot(DefendR.DATA.ID, 4);
        data.addCardSlot(DanHeng.DATA.ID, 1);
        data.addCardSlot(PowderSnow.DATA.ID, 1);
    }

    @Override
    public ArrayList<String> getBaseStartingRelics() {
        return EUIUtils.arrayList(PeriodicTable.DATA.ID);
    }
}
