package pinacolada.actions.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLAction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.AffinityReactions;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;

import java.util.ArrayList;


public class ElementReaction extends PCLAction<AffinityReactions> {
    public final ArrayList<AbstractCreature> creatures;
    public AffinityReactions reactions;
    public PCLAffinity reactor;
    public boolean showEffect = true;

    public ElementReaction(AffinityReactions reactions, AbstractCard card, PCLUseInfo info) {
        super(ActionType.POWER, Settings.ACTION_DUR_XFAST);
        this.reactions = reactions;
        this.card = card;
        this.source = info.source;
        this.target = info.target;
        this.creatures = new ArrayList<>(info.targets);
    }

    @Override
    protected void firstUpdate() {
        if (reactions == null || reactions.isEmpty()) {
            completeImpl();
            return;
        }

        ConjurerReactionMeter.meter.addCount(reactions.sum(), showEffect);
        ConjurerReactionMeter.meter.onReaction(reactions);

        for (AbstractCreature mo : creatures) {
            CombatManager.subscriberDo(OnElementReactSubscriber.class, s -> s.onElementReact(reactions, mo));
            for (AbstractPower po : mo.powers) {
                if (po instanceof AbstractPCLElementalPower) {
                    ((AbstractPCLElementalPower) po).onReact(source, reactions);
                }
            }
        }
    }

    public ElementReaction showEffect(boolean showEffect) {
        this.showEffect = showEffect;

        return this;
    }

    @Override
    protected void updateInternal(float deltaTime) {
        if (tickDuration(deltaTime)) {
            complete(reactions);
        }
    }

}
