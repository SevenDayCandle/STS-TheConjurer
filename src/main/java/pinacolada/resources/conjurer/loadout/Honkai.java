package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.AirCurrent;
import pinacolada.cards.conjurer.series.honkai.March7th;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class Honkai extends ConjurerLoadout {
    public static final String ID = createID(Honkai.class);

    public Honkai() {
        super(ID, 6);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(0, 4).markAllSeen();
        data.getCardSlot(1).select(0, 4).markAllSeen();
        data.getCardSlot(2).select(March7th.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(AirCurrent.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).clear();
        data.getCardSlot(5).clear();
    }
}
