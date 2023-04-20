package pinacolada.actions.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLAction;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.interfaces.subscribers.OnElementReactSubscriber;
import pinacolada.misc.AffinityReactions;
import pinacolada.dungeon.CombatManager;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;


public class ElementReaction extends PCLAction<AffinityReactions>
{
    public AffinityReactions reactions;
    public PCLAffinity reactor;
    public boolean showEffect = true;

    public ElementReaction(AffinityReactions reactions, AbstractCard card, AbstractCreature source, AbstractCreature target)
    {
        super(ActionType.POWER, Settings.ACTION_DUR_XFAST);
        this.reactions = reactions;
        this.card = card;
        this.source = source;
        this.target = target;
    }

    @Override
    protected void firstUpdate()
    {
        if (reactions == null || reactions.isEmpty())
        {
            completeImpl();
            return;
        }

        int sum = reactions.sum();
        ConjurerReactionMeter.meter.addCount(sum, showEffect);

        ArrayList<AbstractCreature> cr = new ArrayList<>();
        if (target != null)
        {
            cr.add(target);
        }
        else
        {
            if (card.target == AbstractCard.CardTarget.ALL || card.target == AbstractCard.CardTarget.ALL_ENEMY)
            {
                cr.addAll(GameUtilities.getEnemies(true));
            }
            if (card.target == AbstractCard.CardTarget.ALL || card.target == AbstractCard.CardTarget.SELF)
            {
                cr.add(AbstractDungeon.player);
            }
        }

        for (AbstractCreature mo : cr)
        {
            CombatManager.subscriberDo(OnElementReactSubscriber.class, s -> s.onElementReact(reactions, mo));
            for (AbstractPower po : mo.powers)
            {
                if (po instanceof AbstractPCLElementalPower)
                {
                    ((AbstractPCLElementalPower) po).onReact(source, reactions, sum);
                }
            }
        }
    }

    @Override
    protected void updateInternal(float deltaTime)
    {
        if (tickDuration(deltaTime))
        {
            complete(reactions);
        }
    }

    public ElementReaction showEffect(boolean showEffect)
    {
        this.showEffect = showEffect;

        return this;
    }

}
