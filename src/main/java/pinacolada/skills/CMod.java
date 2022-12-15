package pinacolada.skills;

import pinacolada.cards.base.PCLAffinity;
import skills.decider.modifiers.PMod_BonusIfAura;

public abstract class CMod
{
    public static PMod bonusIfAura(int amount, PCLAffinity... s)
    {
        return new PMod_BonusIfAura(amount, s);
    }
}
