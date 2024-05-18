package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.DefendP;
import pinacolada.cards.conjurer.basic.StrikeI;
import pinacolada.cards.conjurer.series.eldenring.Parry;
import pinacolada.cards.conjurer.series.eldenring.Quickstep;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class EldenRing extends ConjurerLoadout {
    public static final String ID = createID(EldenRing.class);

    public EldenRing() {
        super(ID, 0);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeI.DATA.ID, 4);
        data.addCardSlot(DefendP.DATA.ID, 4);
        data.addCardSlot(Parry.DATA.ID, 1);
        data.addCardSlot(Quickstep.DATA.ID, 1);
    }
}
