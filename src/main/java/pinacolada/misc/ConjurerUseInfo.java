package pinacolada.misc;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.ui.combat.ConjurerReactionMeter;

import java.util.ArrayList;
import java.util.Collections;

public class ConjurerUseInfo extends PCLUseInfo {
    public AffinityReactions reactions;

    public ConjurerUseInfo(AbstractCard card, AbstractCreature source, AbstractCreature target) {
        super(card, source, target);
    }

    public ConjurerUseInfo(ConjurerUseInfo other) {
        super(other);
        reactions = other.reactions.makeCopy();
    }

    public PCLUseInfo set(AbstractCard card, AbstractCreature source, AbstractCreature target) {
        super.set(card, source, target);
        if (reactions == null) {
            reactions = new AffinityReactions();
        }
        if (card != null) {
            ConjurerReactionMeter.meter.updateReactions(reactions, card,
                    target != null ? Collections.singleton(target) :
                            card.target == AbstractCard.CardTarget.ALL_ENEMY ? enemies
                                    : card.target == AbstractCard.CardTarget.SELF ? Collections.singleton(AbstractDungeon.player) : new ArrayList<>());
        }
        return this;
    }
}
