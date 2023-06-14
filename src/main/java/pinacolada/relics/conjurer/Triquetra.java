package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleRelic
public class Triquetra extends PCLRelic {
    public static final PCLRelicData DATA = register(Triquetra.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL);

    public Triquetra() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            // Simplify element reactions
            for (ConjurerElementButton b : ConjurerReactionMeter.meter.getElementButtons()) {
                b.initialize();
            }
            ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Orange);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addReaction(ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red));
            for (ConjurerElementButton element : ConjurerReactionMeter.meter.getElementButtons()) {
                element.currentCost /= getValue();
            }

        });
    }

    public int getValue() {
        return 2;
    }
}