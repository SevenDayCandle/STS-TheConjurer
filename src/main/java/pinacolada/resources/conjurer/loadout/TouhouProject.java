package pinacolada.resources.conjurer.loadout;

import pinacolada.resources.conjurer.ConjurerLoadout;

public class TouhouProject extends ConjurerLoadout {
    public static final String ID = createID(TouhouProject.class);

    public TouhouProject() {
        super(ID, 4);
    }
}
