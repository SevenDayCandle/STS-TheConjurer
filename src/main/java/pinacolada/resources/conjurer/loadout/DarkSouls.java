package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.DefendO;
import pinacolada.cards.conjurer.basic.Ignite;
import pinacolada.cards.conjurer.basic.StrikeR;
import pinacolada.cards.conjurer.series.darksouls.FlashSweat;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class DarkSouls extends ConjurerLoadout {
    public static final String ID = createID(DarkSouls.class);

    public DarkSouls() {
        super(ID, 3);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeR.DATA.ID, 4);
        data.addCardSlot(DefendO.DATA.ID, 4);
        data.addCardSlot(FlashSweat.DATA.ID, 1);
        data.addCardSlot(Ignite.DATA.ID, 1);
    }
}
