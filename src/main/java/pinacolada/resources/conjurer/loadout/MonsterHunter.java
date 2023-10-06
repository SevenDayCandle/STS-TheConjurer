package pinacolada.resources.conjurer.loadout;

import extendedui.EUIUtils;
import pinacolada.cards.conjurer.basic.DefendG;
import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.basic.StrikeO;
import pinacolada.cards.conjurer.series.monsterhunter.PukeiPukei;
import pinacolada.relics.conjurer.PeriodicTable;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

import java.util.ArrayList;

public class MonsterHunter extends ConjurerLoadout {
    public static final String ID = createID(MonsterHunter.class);

    public MonsterHunter() {
        super(ID, 5);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeO.DATA.ID, 4);
        data.addCardSlot(DefendG.DATA.ID, 4);
        data.addCardSlot(PukeiPukei.DATA.ID, 1);
        data.addCardSlot(Ignite.DATA.ID, 1);
    }

    @Override
    public ArrayList<String> getBaseStartingRelics() {
        return EUIUtils.arrayList(PeriodicTable.DATA.ID);
    }
}
