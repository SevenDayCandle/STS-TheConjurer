package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.providers.PointerProvider;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.conditions.PCond_PayMatter;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class QuadraticGlobe extends PCLRelic implements OnCardCreatedSubscriber {
    public static final PCLRelicData DATA = register(QuadraticGlobe.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.CLINK);

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
                element.setCurrentCostMultiplier(getMultiplier());
            }

            for (AbstractCard c : GameUtilities.getCardsInAnyPile()) {
                modify(c);
            }
            CombatManager.subscribe(this);
        });
    }

    public int getValue() {
        return -10;
    }

    public String getDescriptionImpl() {
        return this.formatDescription(0, this.getValue(), this.getPricePercent());
    }

    public float getMultiplier() {
        return (100 + getPricePercent()) / 100f;
    }

    public int getPricePercent() {
        return 75;
    }

    @Override
    public void onCardCreated(AbstractCard card, boolean b) {
        modify(card);
    }

    protected void modify(AbstractCard card) {
        if (card instanceof PointerProvider) {
            ((PointerProvider) card).doAll(ef -> ef.recurse(subEf -> {
                if (subEf instanceof PCond_PayMatter) {
                    subEf.setAmount((int) (subEf.amount * getMultiplier()));
                }
            }));
        }
    }
}