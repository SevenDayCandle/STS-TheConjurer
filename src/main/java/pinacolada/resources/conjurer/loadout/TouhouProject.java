package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.PowderSnow;
import pinacolada.cards.conjurer.series.touhouproject.YuukaKazami;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class TouhouProject extends ConjurerLoadout {
    public static final String ID = createID(TouhouProject.class);

    public TouhouProject() {
        super(ID, 5);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(0, 4).markAllSeen();
        data.getCardSlot(1).select(0, 4).markAllSeen();
        data.getCardSlot(2).select(YuukaKazami.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(Condensation.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).select(PowderSnow.DATA, 1).markCurrentSeen();
        data.getCardSlot(5).select(null);
    }
}
