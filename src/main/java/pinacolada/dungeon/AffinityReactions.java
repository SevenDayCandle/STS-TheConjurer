package pinacolada.dungeon;

import pinacolada.cards.base.fields.PCLAffinity;

import java.util.HashMap;

public class AffinityReactions {
    public final HashMap<PCLAffinity, HashMap<PCLAffinity, Integer>> reactions = new HashMap<>();

    public AffinityReactions() {
    }

    public void addReaction(PCLAffinity dest, PCLAffinity reactor, int amount) {
        HashMap<PCLAffinity, Integer> amp = reactions.getOrDefault(dest, new HashMap<>());
        amp.merge(reactor, amount, Integer::sum);
        reactions.putIfAbsent(dest, amp);
    }

    public void clear() {
        reactions.clear();
    }

    public int getValue(PCLAffinity affinity) {
        int sum = 0;
        if (reactions.containsKey(affinity)) {
            for (Integer value : reactions.get(affinity).values()) {
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
        for (PCLAffinity affinity : reactions.keySet()) {
            for (Integer value : reactions.get(affinity).values()) {
                sum += value;
            }
        }
        return sum;
    }
}