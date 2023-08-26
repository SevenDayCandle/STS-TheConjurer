package pinacolada.resources.conjurer;

import pinacolada.blights.conjurer.KnotTheory;
import pinacolada.blights.conjurer.MolecularDiffusion;
import pinacolada.relics.conjurer.DisguisePropBox;
import pinacolada.resources.PGR;
import pinacolada.resources.loadout.LoadoutBlightSlot;
import pinacolada.resources.loadout.LoadoutRelicSlot;
import pinacolada.resources.loadout.PCLLoadout;

public class ConjurerLoadout extends PCLLoadout {
    protected static final int MAX_VALUE = 15;

    public ConjurerLoadout() {
        this(createID(ConjurerLoadout.class), -1);
    }

    public ConjurerLoadout(String id, int unlockLevel) {
        super(ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel, MAX_VALUE, MIN_CARDS);
    }

    public ConjurerLoadout(String id) {
        this(id, 0);
    }

    public static String createID(Class<? extends PCLLoadout> type) {
        return createID(ConjurerResources.ID, type);
    }

    public static ConjurerLoadout generate(String prefix) {
        return generate(prefix, 0);
    }

    public static ConjurerLoadout generate(String prefix, int unlockLevel) {
        return register(new ConjurerLoadout(PGR.createID(ConjurerResources.ID, prefix), unlockLevel));
    }

    public void addLoadoutBlights(LoadoutBlightSlot r1) {
        super.addLoadoutBlights(r1);
        r1.addItem(new MolecularDiffusion(), 0);
        r1.addItem(new KnotTheory(), 0);
    }

    public void addLoadoutRelics(LoadoutRelicSlot r1) {
        super.addLoadoutRelics(r1);
        r1.addItem(new DisguisePropBox(), 12);
    }

    @Override
    public int getSlotsForAbility() {
        return 1;
    }
}
