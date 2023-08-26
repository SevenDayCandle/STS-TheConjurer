package pinacolada.resources.conjurer.loadout;

import pinacolada.cards.conjurer.basic.PowderSnow;
import pinacolada.cards.conjurer.series.darksouls.AcidSurge;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

public class DarkSouls extends ConjurerLoadout {
    public static final String ID = createID(DarkSouls.class);

    public DarkSouls() {
        super(ID, 3);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.getCardSlot(0).select(1, 4).markAllSeen();
        data.getCardSlot(1).select(1, 4).markAllSeen();
        data.getCardSlot(2).select(AcidSurge.DATA, 1).markCurrentSeen();
        data.getCardSlot(3).select(PowderSnow.DATA, 1).markCurrentSeen();
        data.getCardSlot(4).clear();
        data.getCardSlot(5).clear();
    }
}
