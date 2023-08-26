package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.Lithosphere;
import pinacolada.cards.conjurer.series.eldenring.BeastClaw;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class EldenRing extends ConjurerLoadout {
    public static final String ID = createID(EldenRing.class);

    public EldenRing() {
        super(ID, 0);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(1, 4).markAllSeen();
        data.getCardSlot(1).select(1, 4).markAllSeen();
        data.getCardSlot(2).select(BeastClaw.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(Lithosphere.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).clear();
        data.getCardSlot(5).clear();
    }
}
