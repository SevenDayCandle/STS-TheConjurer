package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.DefendP;
import pinacolada.cards.conjurer.basic.StrikeI;
import pinacolada.cards.conjurer.series.darksouls.DarkEdge;
import pinacolada.cards.conjurer.series.darksouls.EmitForce;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class DarkSouls extends ConjurerLoadout {
    public static final String ID = createID(DarkSouls.class);

    public DarkSouls() {
        super(ID, 5);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeI.DATA.ID, 4);
        data.addCardSlot(DefendP.DATA.ID, 4);
        data.addCardSlot(EmitForce.DATA.ID, 1);
        data.addCardSlot(DarkEdge.DATA.ID, 1);
    }
}
