package pinacolada.skills;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.PCLTooltips;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.conjurer.ConjurerTooltips;
import pinacolada.skills.conjurer.moves.PMove_ApplyElement;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;

public abstract class CMove
{
    public static PMove applyElement(PCLCardTarget target, int amount, PCLAffinity... affinities)
    {
        return new PMove_ApplyElement(target, amount, affinities);
    }

    public static PMove applyElementToAll(int amount, PCLAffinity... affinities)
    {
        return applyElement(PCLCardTarget.All, amount, affinities);
    }

    public static PMove applyElementToAllies(int amount, PCLAffinity... affinities)
    {
        return applyElement(PCLCardTarget.AllAlly, amount, affinities);
    }

    public static PMove applyElementToEnemies(int amount, PCLAffinity... affinities)
    {
        return applyElement(PCLCardTarget.AllEnemy, amount, affinities);
    }

    public static PMove applyElementToRandom(int amount, PCLAffinity... affinities)
    {
        return applyElement(PCLCardTarget.RandomEnemy, amount, affinities);
    }

    public static PMove applyElementToSingle(int amount, PCLAffinity... affinities)
    {
        return applyElement(PCLCardTarget.Single, amount, affinities);
    }

    public static PMove applyElementToSelf(int amount, PCLAffinity... affinities)
    {
        return applyElement(PCLCardTarget.Self, amount, affinities);
    }

    public static PMove gainReaction(int amount)
    {
        return new PMove_GainReaction(amount);
    }
}
