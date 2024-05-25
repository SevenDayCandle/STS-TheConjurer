package pinacolada.actions.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLAction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.*;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;

import java.util.ArrayList;
import java.util.List;


public class ElementReaction extends PCLAction<AffinityReactions> {
    public final ArrayList<AbstractCreature> creatures;
    public AffinityReactions reactions;
    public PCLAffinity reactor;
    public boolean showEffect = true;

    public ElementReaction(AffinityReactions reactions, AbstractCard card, PCLUseInfo info) {
        this(reactions, card, info.source, info.target, info.targets);
    }

    public ElementReaction(AffinityReactions reactions, AbstractCard card, AbstractCreature source, AbstractCreature target, List<? extends AbstractCreature> targets) {
        super(ActionType.POWER, Settings.ACTION_DUR_XFAST);
        this.reactions = reactions;
        this.card = card;
        this.source = source;
        this.target = target;
        this.creatures = new ArrayList<>(targets);
    }

    @Override
    protected void firstUpdate() {
        if (reactions == null || reactions.isEmpty()) {
            completeImpl();
            return;
        }

        ConjurerReactionMeter.meter.onReaction(reactions, showEffect);

        for (AbstractCreature mo : creatures) {
            PCLUseInfo info = CombatManager.playerSystem.generateInfo(card, source, mo);
            boolean shouldConsume = CombatManager.subscriberCanDeny(OnElementReactSubscriber.class, s -> s.onElementReact(info, reactions, mo));
            for (AbstractPower po : mo.powers) {
                for (ConjurerElementButton button : ConjurerReactionMeter.meter.getElementButtons()) {
                    button.onReact(info, reactions, po, shouldConsume);
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
