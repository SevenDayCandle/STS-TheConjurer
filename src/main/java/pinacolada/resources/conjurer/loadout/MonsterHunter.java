package pinacolada.resources.conjurer.loadout;

import pinacolada.resources.conjurer.ConjurerLoadout;

public class MonsterHunter extends ConjurerLoadout {
    public static final String ID = createID(MonsterHunter.class);

    public MonsterHunter() {
        super(ID, 5);
    }
}
