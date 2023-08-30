package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.skills.conjurer.conditions.*;

public abstract class CCond {

    public static PCond_CheckLevel checkLevel(int amount, PCLAffinity... aff) {
        return new PCond_CheckLevel(amount, aff);
    }

    public static PCond_CheckMatter checkMatter(int amount) {
        return new PCond_CheckMatter(amount);
    }

    public static PCond_AllyLink link(PCLAffinity... affinities) {
        return new PCond_AllyLink(0, affinities);
    }

    public static PCond_AllyLink linkBack(PCLAffinity... affinities) {
        return new PCond_AllyLink(-1, affinities);
    }

    public static PCond_AllyLink linkFront(PCLAffinity... affinities) {
        return new PCond_AllyLink(1, affinities);
    }

    public static PCond_PayMatter payMatter(int amount) {
        return new PCond_PayMatter(amount);
    }

    public static PCond_React react(PCLAffinity... affinities) {
        return new PCond_React(affinities);
    }
}
