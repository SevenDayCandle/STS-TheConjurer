package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.skills.conjurer.conditions.*;

public abstract class CCond {

    public static PCond_CheckLevel checkLevel(int amount, PCLAffinity... aff) {
        return new PCond_CheckLevel(amount, aff);
    }

    public static PCond_CheckReaction checkReaction(int amount) {
        return new PCond_CheckReaction(amount);
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

    public static PCond_PayLevel payLevel(int amount, PCLAffinity... aff) {
        return new PCond_PayLevel(amount, aff);
    }

    public static PCond_React react(PCLAffinity... affinities) {
        return new PCond_React(affinities);
    }
}
