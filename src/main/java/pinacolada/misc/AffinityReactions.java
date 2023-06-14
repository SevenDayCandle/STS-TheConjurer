package pinacolada.misc;

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

    public boolean hasReaction(PCLAffinity aff) {
        return reactions.containsKey(aff);
    }

    public boolean isEmpty() {
        return !hasReaction();
    }

    public boolean hasReaction() {
        return !reactions.isEmpty();
    }

    public int getValue(PCLAffinity affinity)
    {
        int sum = 0;
        if (reactions.containsKey(affinity))
        {
            for (Integer value : reactions.get(affinity).values()) {
                sum += value;
            }
        }
        return sum;
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
