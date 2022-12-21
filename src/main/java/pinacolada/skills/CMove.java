package pinacolada.skills;

import pinacolada.skills.conjurer.moves.PMove_GainReaction;

public abstract class CMove
{
    public static PMove gainReaction(int amount)
    {
        return new PMove_GainReaction(amount);
    }
}
