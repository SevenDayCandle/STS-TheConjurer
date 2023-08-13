package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.skills.conjurer.conditions.PCond_Affix;
import pinacolada.skills.conjurer.conditions.PCond_CheckMatter;
import pinacolada.skills.conjurer.conditions.PCond_PayMatter;
import pinacolada.skills.conjurer.conditions.PCond_React;

public abstract class CCond {

    public static PCond_Affix affix(PCLAffinity... affinities) {
        return new PCond_Affix(0, affinities);
    }

    public static PCond_Affix affixBack(PCLAffinity... affinities) {
        return new PCond_Affix(-1, affinities);
    }

    public static PCond_Affix affixFront(PCLAffinity... affinities) {
        return new PCond_Affix(1, affinities);
    }

    public static PCond_CheckMatter checkMatter(int amount) {
        return new PCond_CheckMatter(amount);
    }

    public static PCond_PayMatter payMatter(int amount) {
        return new PCond_PayMatter(amount);
    }

    public static PCond_React react(PCLAffinity... affinities) {
        return new PCond_React(affinities);
    }
}
