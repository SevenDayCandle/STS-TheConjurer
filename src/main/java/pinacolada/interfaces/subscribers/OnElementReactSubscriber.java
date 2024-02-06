package pinacolada.interfaces.subscribers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.CombatSubscriber;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.PCLUseInfo;

@CombatSubscriber
public interface OnElementReactSubscriber extends PCLCombatSubscriber {
    void onElementReact(PCLUseInfo info, AffinityReactions reactions, AbstractCreature m);
}