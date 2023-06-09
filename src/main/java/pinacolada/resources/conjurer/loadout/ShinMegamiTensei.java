package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.Lithosphere;
import pinacolada.cards.conjurer.series.shinmegamitensei.PyroJack;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class ShinMegamiTensei extends ConjurerLoadout {
    public static final String ID = createID(ShinMegamiTensei.class);

    public ShinMegamiTensei() {
        super(ID, 1);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(1, 4).markAllSeen();
        data.getCardSlot(1).select(0, 4).markAllSeen();
        data.getCardSlot(2).select(PyroJack.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(Condensation.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).select(Lithosphere.DATA, 1).markCurrentSeen();
        data.getCardSlot(5).select(null);
    }
}
