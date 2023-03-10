package pinacolada.interfaces.subscribers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.CombatSubscriber;
import pinacolada.misc.AffinityReactions;

@CombatSubscriber
public interface OnElementReactSubscriber extends PCLCombatSubscriber
{
    void onElementReact(AffinityReactions reactions, AbstractCreature m);
}