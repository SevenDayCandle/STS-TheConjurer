package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.interfaces.providers.PointerProvider;
import pinacolada.interfaces.subscribers.OnCardCreatedSubscriber;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.conditions.PCond_PayMatter;
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
                element.currentAmplifyOffset = AbstractPCLElementalPower.BASE_DAMAGE_MULTIPLIER * -getValue() / 100;
                element.setCurrentCostMultiplier(getMultiplier());
                CombatManager.addEffectBonus(element.elementID(), -getValue());
            }

            for (AbstractCard c : GameUtilities.getCardsInAnyPile()) {
                modify(c);
            }
            CombatManager.subscribe(this);
        });
    }

    public int getValue() {
        return 50;
    }

    public float getMultiplier() {
        return (100 + getValue()) / 100f;
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