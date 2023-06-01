package pinacolada.resources.conjurer.loadout;

import pinacolada.resources.conjurer.ConjurerLoadout;

public class Atelier extends ConjurerLoadout {
    public static final String ID = createID(Atelier.class);

    public Atelier() {
        super(ID, 5);
    }
}
