package pinacolada.skills;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.skills.conjurer.conditions.PCond_Combust;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;
import pinacolada.skills.conjurer.conditions.PCond_Redox;

public abstract class CCond
{
    public static PCond combust(PCLAffinity... affinities)
    {
        return new PCond_Combust(affinities);
    }

    public static PCond payReaction(int amount)
    {
        return new PCond_PayReaction(amount);
    }

    public static PCond redox(PCLAffinity... affinities)
    {
        return new PCond_Redox(affinities);
    }
}
