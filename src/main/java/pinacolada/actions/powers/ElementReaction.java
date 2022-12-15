package pinacolada.actions.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActionWithCallback;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.misc.CombatManager;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;
import java.util.Map;


public class ElementReaction extends PCLActionWithCallback<AffinityReactions>
{
    public AffinityReactions reactions;
    public PCLAffinity reactor;
    public boolean showEffect = true;

    public ElementReaction(AffinityReactions reactions, AbstractCard card, AbstractCreature target)
    {
        super(ActionType.POWER, Settings.ACTION_DUR_XFAST);
        this.reactions = reactions;
        this.card = card;
        this.target = target;
    }

    @Override
    protected void firstUpdate()
    {
        if (reactions == null || reactions.isEmpty())
        {
            complete();
            return;
        }

        for (PCLAffinity affinity : reactions.combustions.keySet())
        {
            for (Map.Entry<PCLAffinity, Integer> item : reactions.combustions.get(affinity).entrySet())
            {
                ConjurerReactionMeter.meter.addCount(item.getValue(), showEffect);
            }
        }
        for (PCLAffinity affinity : reactions.redoxes.keySet())
        {
            for (Map.Entry<PCLAffinity, Integer> item : reactions.redoxes.get(affinity).entrySet())
            {
                ConjurerReactionMeter.meter.addCount(item.getValue(), showEffect);
            }
        }

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
            CombatManager.onElementReact(reactions, mo);
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
