package pinacolada.skills;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import skills.decider.moves.PMove_ActivateAura;
import skills.decider.moves.PMove_GainAura;
import skills.decider.moves.PMove_Shift;

public abstract class CMove
{
    public static PMove activateAura(PCLAffinity... amount)
    {
        return new PMove_ActivateAura(amount);
    }

    public static PMove gainAura(int amount, PCLAffinity... affinities)
    {
        return new PMove_GainAura(amount, affinities);
    }

    public static PMove gainAura(PCLCard card, PSkill.PCLCardValueSource valueSource, PCLAffinity... affinities)
    {
        return new PMove_GainAura(0, affinities)
                .setSource(card, valueSource);
    }

    public static PMove gainReaction(int amount)
    {
        return new PMove_GainReaction(amount);
    }

    public static PMove shift(PCLAffinity... amount)
    {
        return new PMove_Shift(amount);
    }
}
