package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.DefendR;
import pinacolada.cards.conjurer.basic.StrikeB;
import pinacolada.cards.conjurer.series.shinmegamitensei.PyroJack;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class ShinMegamiTensei extends ConjurerLoadout {
    public static final String ID = createID(ShinMegamiTensei.class);

    public ShinMegamiTensei() {
        super(ID, 2);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeB.DATA.ID, 4);
        data.addCardSlot(DefendR.DATA.ID, 4);
        data.addCardSlot(PyroJack.DATA.ID, 1);
        data.addCardSlot(Condensation.DATA.ID, 1);
    }
}
