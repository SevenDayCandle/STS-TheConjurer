package pinacolada.skills;

import pinacolada.skills.conjurer.moves.PMove_GainMatter;

public abstract class CMove {
    public static PMove_GainMatter gainMatter(int amount) {
        return new PMove_GainMatter(amount);
    }
}
