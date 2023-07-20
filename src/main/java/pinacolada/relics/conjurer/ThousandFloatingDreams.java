package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.conjurer.colorless.Cactuar;
import pinacolada.cards.conjurer.series.genshinimpact.Cyno;
import pinacolada.cards.conjurer.series.genshinimpact.Nahida;
import pinacolada.cards.conjurer.series.genshinimpact.Nilou;
import pinacolada.cards.conjurer.series.genshinimpact.Tighnari;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.RandomizedList;

@VisibleRelic
public class ThousandFloatingDreams extends PCLRelic {
    public static final PCLRelicData DATA = register(ThousandFloatingDreams.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.MAGICAL);
    protected AbstractCard card;

    public ThousandFloatingDreams() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        RandomizedList<AbstractCard> possible = new RandomizedList<>(new Cyno(), new Nilou(), new Nahida(), new Tighnari(), new Cactuar());
        CardGroup choices = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        while (choices.size() < getValue() && !possible.isEmpty()) {
            choices.addToBottom(possible.retrieve(rng, true));
        }

        PCLActions.bottom.selectFromPile(name, 1, choices)
                .addCallback((cards) -> {
                    for (AbstractCard c : cards) {
                        card = c;
                        if (card instanceof PCLCard) {
                            PCLActions.bottom.summonAlly((PCLCard) card, null);
                        }
                    }
                });
    }

    public void onVictory() {
        super.onVictory();

        AbstractRoom curRoom = GameUtilities.getCurrentRoom();
        if (card != null && curRoom != null && GameUtilities.chance(10f)) {
            for (RewardItem item : curRoom.rewards) {
                if (item.type == RewardItem.RewardType.CARD) {
                    item.cards.add(card);
                    flash();
                    break;
                }
            }
        }

        card = null;
    }

    public int getValue() {
        return 3;
    }
}