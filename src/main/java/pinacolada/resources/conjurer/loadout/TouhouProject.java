package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.basic.DefendB;
import pinacolada.cards.conjurer.basic.StrikeG;
import pinacolada.cards.conjurer.series.touhouproject.SuwakoMoriya;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class TouhouProject extends ConjurerLoadout {
    public static final String ID = createID(TouhouProject.class);

    public TouhouProject() {
        super(ID, 4);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeG.DATA.ID, 4);
        data.addCardSlot(DefendB.DATA.ID, 4);
        data.addCardSlot(SuwakoMoriya.DATA.ID, 1);
        data.addCardSlot(Condensation.DATA.ID, 1);
    }
}
