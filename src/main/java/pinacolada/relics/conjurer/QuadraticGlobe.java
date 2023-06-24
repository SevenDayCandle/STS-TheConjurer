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
public class QuadraticGlobe extends PCLRelic {
    public static final PCLRelicData DATA = register(QuadraticGlobe.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL);

    public QuadraticGlobe() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Blue).addReaction(PCLAffinity.Red);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red).addReaction(PCLAffinity.Orange);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Orange).addReaction(PCLAffinity.Green);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addReaction(PCLAffinity.Blue);
            for (ConjurerElementButton element : ConjurerReactionMeter.meter.getElementButtons()) {
                element.currentAmplifyOffset = getValue();
                element.currentCost = (int) (element.currentCost * (100 + getPricePercent()) / 100f);
            }
        });
    }

    public String getUpdatedDescription() {
        return this.formatDescription(0, this.getValue(), this.getPricePercent());
    }

    public int getValue() {
        return -10;
    }

    public int getPricePercent() {
        return 50;
    }
}