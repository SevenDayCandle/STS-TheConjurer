package pinacolada.dungeon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.PCLEnum;

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
            // If the card is a summon that is played, it can only target a single slot
            if (card.type == PCLEnum.CardType.SUMMON && !(source instanceof PCLCardAlly && ((PCLCardAlly) source).card == card)) {
                ConjurerReactionMeter.meter.updateReactions(reactions, card, target != null ? Collections.singleton(target) : Collections.emptyList());
            }
            else {
                ConjurerReactionMeter.meter.updateReactions(reactions, card, targets);
            }
        }
        return this;
    }
}
