package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.series.monsterhunter.PukeiPukei;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class MonsterHunter extends ConjurerLoadout {
    public static final String ID = createID(MonsterHunter.class);

    public MonsterHunter() {
        super(ID, 5);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(0, 4).markAllSeen();
        data.getCardSlot(1).select(1, 4).markAllSeen();
        data.getCardSlot(2).select(PukeiPukei.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(Ignite.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).clear();
        data.getCardSlot(5).clear();
    }
}
