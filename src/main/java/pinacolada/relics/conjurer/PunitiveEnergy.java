package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.OnIntensifySubscriber;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

import java.util.HashSet;

@VisibleRelic
public class PunitiveEnergy extends PCLRelic implements OnIntensifySubscriber {
    public static final PCLRelicData DATA = register(PunitiveEnergy.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL);
    protected HashSet<PCLAffinity> disabled = new HashSet<>();

    public PunitiveEnergy() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        CombatManager.subscribe(this);
    }

    @Override
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        for (PCLAffinity affinity : disabled) {
            ConjurerReactionMeter.meter.getElementButton(affinity).setLevelEnabled(true);
        }
        disabled.clear();
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public void onIntensify(PCLAffinity pclAffinity) {
        for (ConjurerElementButton b : ConjurerReactionMeter.meter.getElementButtons()) {
            if (b.canInteract) {
                b.setLevelEnabled(false);
                disabled.add(b.affinity);
            }
        }
    }
}