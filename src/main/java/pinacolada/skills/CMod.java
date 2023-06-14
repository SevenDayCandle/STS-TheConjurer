package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.skills.conjurer.modifiers.PMod_BonusOnReact;
import pinacolada.skills.conjurer.modifiers.PMod_PerElement;
import pinacolada.skills.conjurer.modifiers.PMod_PerMatter;

public abstract class CMod {
    public static PMod_BonusOnReact bonusOnReact(int amount) {
        return new PMod_BonusOnReact(amount);
    }

    public static PMod_PerElement perElement(int amount, PCLAffinity... s) {
        return new PMod_PerElement(amount, s);
    }

    public static PMod_PerElement perElement(PCLCardTarget target, int amount, PCLAffinity... s) {
        return new PMod_PerElement(target, amount, s);
    }

    public static PMod_PerMatter perMatter(int amount) {
        return new PMod_PerMatter(amount);
    }
}
