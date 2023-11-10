package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.dungeon.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class TreasureChest_TreasureKey extends PCLCard {
    public static final PCLCardData DATA = register(TreasureChest_TreasureKey.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL)
            .setTags(PCLCardTag.Purge.make(1))
            .setCore(true);

    public TreasureChest_TreasureKey() {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info, PCLActions order) {
        order.selectFromPile(move.getName(), 1, AbstractDungeon.player.hand)
                .setFilter(c -> TreasureChest.DATA.ID.equals(c.cardID))
                .addCallback(cards -> {
                    for (AbstractCard c : cards) {
                        GameUtilities.modifyTag(c, PCLCardTag.Unplayable, 0);
                    }
                });
    }

    public void setup(Object input) {
        addUseMove(PMove.draw(1).setUpgrade(1));
        addSpecialMove(0, this::action);
    }
}