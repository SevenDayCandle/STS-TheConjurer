package pinacolada.resources.conjurer.loadout;

import extendedui.EUIUtils;
import pinacolada.cards.conjurer.basic.DefendP;
import pinacolada.cards.conjurer.basic.StrikeA;
import pinacolada.cards.conjurer.basic.StrikeI;
import pinacolada.cards.conjurer.series.darksouls.AcidSurge;
import pinacolada.cards.conjurer.series.darksouls.DarkEdge;
import pinacolada.cards.conjurer.series.darksouls.EmitForce;
import pinacolada.cards.conjurer.series.darksouls.Fireball;
import pinacolada.relics.conjurer.AcademyScroll;
import pinacolada.relics.conjurer.SoulOfACrystalSage;
import pinacolada.resources.conjurer.ConjurerLoadout;
import pinacolada.resources.loadout.PCLLoadoutData;

import java.util.ArrayList;

public class DarkSouls extends ConjurerLoadout {
    public static final String ID = createID(DarkSouls.class);

    public DarkSouls() {
        super(ID, 5);
    }

    protected void setDefaultCardsForData(PCLLoadoutData data) {
        data.addCardSlot(StrikeA.DATA.ID, 4);
        data.addCardSlot(DefendP.DATA.ID, 4);
        data.addCardSlot(Fireball.DATA.ID, 1);
        data.addCardSlot(AcidSurge.DATA.ID, 1);
    }

    @Override
    public ArrayList<String> getBaseStartingRelics() {
        return EUIUtils.arrayList(SoulOfACrystalSage.DATA.ID);
    }
}
