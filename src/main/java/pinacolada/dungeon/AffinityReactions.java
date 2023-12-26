package pinacolada.dungeon;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.fields.PCLAffinity;

import java.util.HashMap;

public class AffinityReactions {
    public final HashMap<PCLAffinity, HashMap<AbstractCreature, HashMap<PCLAffinity, Integer>>> reactions = new HashMap<>();

    public AffinityReactions() {
    }

    public void addReaction(PCLAffinity dest, PCLAffinity reactor, AbstractCreature target, int amount) {
        HashMap<AbstractCreature, HashMap<PCLAffinity, Integer>> targetMap = reactions.getOrDefault(dest, new HashMap<>());
        HashMap<PCLAffinity, Integer> amp = targetMap.getOrDefault(target, new HashMap<>());
        amp.merge(reactor, amount, Integer::sum);
        targetMap.putIfAbsent(target, amp);
        reactions.putIfAbsent(dest, targetMap);
    }

    public void clear() {
        reactions.clear();
    }

    public HashMap<PCLAffinity, Integer> getReactants(PCLAffinity affinity, AbstractCreature target) {
        HashMap<AbstractCreature, HashMap<PCLAffinity, Integer>> targetMap = reactions.get(affinity);
        if (targetMap != null && targetMap.containsKey(target)) {
            return targetMap.get(target);
        }
        return null;
    }

    public int getValue(PCLAffinity affinity, AbstractCreature target) {
        int sum = 0;
        HashMap<AbstractCreature, HashMap<PCLAffinity, Integer>> targetMap = reactions.get(affinity);
        if (targetMap != null && targetMap.containsKey(target)) {
            for (Integer value : targetMap.get(target).values()) {
                sum += value;
            }
        }
        return sum;
    }

    public boolean hasReaction() {
        return !reactions.isEmpty();
    }

    public boolean hasReaction(PCLAffinity aff) {
        return reactions.containsKey(aff);
    }

    public boolean isEmpty() {
        return !hasReaction();
    }

    public AffinityReactions makeCopy() {
        AffinityReactions copy = new AffinityReactions();
        for (PCLAffinity af : reactions.keySet()) {
            copy.reactions.put(af, new HashMap<>(reactions.get(af)));
        }
        return copy;
    }

    public int sum() {
        int sum = 0;
        for (HashMap<AbstractCreature, HashMap<PCLAffinity, Integer>> targetMap : reactions.values()) {
            for (HashMap<PCLAffinity, Integer> reactMap : targetMap.values()) {
                for (Integer value : reactMap.values()) {
                    sum += value;
                }
            }
        }
        return sum;
    }
}
