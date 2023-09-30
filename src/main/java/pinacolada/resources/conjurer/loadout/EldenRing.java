package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.DefendO;
import pinacolada.cards.conjurer.basic.Lithosphere;
import pinacolada.cards.conjurer.basic.StrikeR;
import pinacolada.cards.conjurer.series.eldenring.BeastClaw;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class EldenRing extends ConjurerLoadout {
    public static final String ID = createID(EldenRing.class);

    public EldenRing() {
        super(ID, 0);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeR.DATA.ID, 4);
        data.addCardSlot(DefendO.DATA.ID, 4);
        data.addCardSlot(BeastClaw.DATA.ID, 1);
        data.addCardSlot(Lithosphere.DATA.ID, 1);
    }
}
