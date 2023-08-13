package pinacolada.misc;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.dungeon.PCLUseInfo;

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
            ConjurerReactionMeter.meter.updateReactions(reactions, card, targets);
        }
        return this;
    }
}
