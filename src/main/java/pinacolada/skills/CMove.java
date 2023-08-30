package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.skills.conjurer.moves.PMove_AddLevel;
import pinacolada.skills.conjurer.moves.PMove_GainMatter;
import pinacolada.skills.conjurer.moves.PMove_StabilizePower;

public abstract class CMove {
    public static PMove_AddLevel addLevel(int amount, PCLAffinity... a) {
        return new PMove_AddLevel(amount, a);
    }

    public static PMove_GainMatter gainMatter(int amount) {
        return new PMove_GainMatter(amount);
    }

    public static PMove_StabilizePower stabilize(PCLCardTarget target, PCLPowerHelper... helpers) {
        return new PMove_StabilizePower(target, helpers);
    }
}
