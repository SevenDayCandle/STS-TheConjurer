package pinacolada.resources.conjurer.loadout;

import extendedui.EUIUtils;
import pinacolada.cards.conjurer.basic.AirCurrent;
import pinacolada.cards.conjurer.basic.DefendB;
import pinacolada.cards.conjurer.basic.StrikeG;
import pinacolada.cards.conjurer.series.genshinimpact.Noelle;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

import java.util.ArrayList;

public class GenshinImpact extends ConjurerLoadout {
    public static final String ID = createID(GenshinImpact.class);

    public GenshinImpact() {
        super(ID, 1);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeG.DATA.ID, 4);
        data.addCardSlot(DefendB.DATA.ID, 4);
        data.addCardSlot(Noelle.DATA.ID, 1);
        data.addCardSlot(AirCurrent.DATA.ID, 1);
    }

    @Override
    public ArrayList<String> getBaseStartingRelics() {
        return EUIUtils.arrayList(PeriodicTable.DATA.ID);
    }
}
