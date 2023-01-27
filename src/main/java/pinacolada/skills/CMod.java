package pinacolada.skills;

import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.skills.conjurer.modifiers.PMod_BonusOnCombust;
import pinacolada.skills.conjurer.modifiers.PMod_BonusOnRedox;
import pinacolada.skills.conjurer.modifiers.PMod_PerElement;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

public abstract class CMod
{
    public static PMod_BonusOnCombust bonusOnCombust(int amount)
    {
        return new PMod_BonusOnCombust(amount);
    }

    public static PMod_BonusOnRedox bonusOnRedox(int amount)
    {
        return new PMod_BonusOnRedox(amount);
    }

    public static PMod_PerElement perElement(int amount, PCLAffinity... s)
    {
        return new PMod_PerElement(amount, s);
    }

    public static PMod_PerElement perElement(PCLCardTarget target, int amount, PCLAffinity... s)
    {
        return new PMod_PerElement(target, amount, s);
    }

    public static PMod_PerReaction perReaction(int amount)
    {
        return new PMod_PerReaction(amount);
    }
}
