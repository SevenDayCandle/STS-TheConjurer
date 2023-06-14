package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.AirCurrent;
import pinacolada.cards.conjurer.basic.Condensation;
import pinacolada.cards.conjurer.series.genshinimpact.Amber;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class GenshinImpact extends ConjurerLoadout {
    public static final String ID = createID(GenshinImpact.class);

    public GenshinImpact() {
        super(ID, 1);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(0, 4).markAllSeen();
        data.getCardSlot(1).select(0, 4).markAllSeen();
        data.getCardSlot(2).select(Amber.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(AirCurrent.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).select(Condensation.DATA, 1).markCurrentSeen();
        data.getCardSlot(5).select(null);
    }
}
