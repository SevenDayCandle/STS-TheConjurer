package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.series.touhouproject.Cirno;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class TouhouProject extends ConjurerLoadout {
    public static final String ID = createID(TouhouProject.class);

    public TouhouProject() {
        super(ID, 4);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(1, 4).markAllSeen();
        data.getCardSlot(1).select(0, 4).markAllSeen();
        data.getCardSlot(2).select(Cirno.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(Condensation.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).clear();
        data.getCardSlot(5).clear();
    }
}
