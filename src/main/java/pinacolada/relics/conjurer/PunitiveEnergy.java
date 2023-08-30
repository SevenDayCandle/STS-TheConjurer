package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.interfaces.subscribers.OnIntensifySubscriber;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class PunitiveEnergy extends PCLRelic implements OnIntensifySubscriber {
    public static final PCLRelicData DATA = register(PunitiveEnergy.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.honkai);

    public PunitiveEnergy() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        CombatManager.subscribe(this);
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
            }
        }
    }
}