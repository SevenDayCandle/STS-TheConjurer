package pinacolada.interfaces.subscribers;

import pinacolada.annotations.CombatSubscriber;
import pinacolada.cards.base.fields.PCLAffinity;

@CombatSubscriber
public interface OnManualIntensifySubscriber extends PCLCombatSubscriber {
    void onManualIntensify(PCLAffinity affinity);
}