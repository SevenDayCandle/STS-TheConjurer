package pinacolada.skills;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.skills.conjurer.modifiers.PMod_PerElement;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

public abstract class CMod
{
    public static PMod perElement(int amount, PCLAffinity... s)
    {
        return new PMod_PerElement(amount, s);
    }

    public static PMod perElement(PCLCardTarget target, int amount, PCLAffinity... s)
    {
        return new PMod_PerElement(target, amount, s);
    }

    public static PMod perReaction(int amount)
    {
        return new PMod_PerReaction(amount);
    }
}
