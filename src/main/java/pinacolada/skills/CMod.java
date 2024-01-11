package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.skills.conjurer.modifiers.PMod_BonusOnReact;
import pinacolada.skills.conjurer.modifiers.PMod_PerLevel;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

public abstract class CMod {
    public static PMod_BonusOnReact bonusOnReact(int amount) {
        return new PMod_BonusOnReact(amount);
    }

    public static PMod_PerLevel bonusPerLevel(int amount, PCLAffinity... a) {
        return (PMod_PerLevel) new PMod_PerLevel(amount, a).edit(f -> f.setNot(true));
    }

    public static PMod_PerLevel perLevel(int amount, PCLAffinity... a) {
        return new PMod_PerLevel(amount, a);
    }

    public static PMod_PerReaction perReaction(int amount) {
        return new PMod_PerReaction(amount);
    }
}
