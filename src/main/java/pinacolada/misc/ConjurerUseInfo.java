package pinacolada.misc;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.ui.combat.ConjurerReactionMeter;

import java.util.ArrayList;
import java.util.Collections;

public class ConjurerUseInfo extends PCLUseInfo
{
    public final AffinityReactions reactions;
    public ConjurerUseInfo(AbstractCard card, AbstractCreature source, AbstractCreature target)
    {
        super(card, source, target);
        if (card != null)
        {
            this.reactions = ConjurerReactionMeter.meter.getReactions(card,
                    target != null ? Collections.singleton(target) :
                            card.target == AbstractCard.CardTarget.ALL_ENEMY ? enemies
                                    : card.target == AbstractCard.CardTarget.SELF ? Collections.singleton(AbstractDungeon.player) : new ArrayList<>());
        }
        else
        {
            this.reactions = new AffinityReactions();
        }
    }
}
