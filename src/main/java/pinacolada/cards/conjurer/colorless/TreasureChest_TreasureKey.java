package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.misc.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class TreasureChest_TreasureKey extends PCLCard
{
    public static final PCLCardData DATA = register(TreasureChest_TreasureKey.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL)
            .setTags(PCLCardTag.Haste.make(1, 2), PCLCardTag.Purge.make(1))
            .setColorless();

    public TreasureChest_TreasureKey()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.gain(3, PCLPowerHelper.NextTurnBlock).setUpgrade(4));
        addSpecialMove(0, this::action);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.selectFromPile(move.getName(), 1, player.hand)
                .setFilter(c -> TreasureChest.DATA.ID.equals(c.cardID))
                .addCallback(cards -> {
                   for (AbstractCard c : cards) {
                       GameUtilities.modifyTag(c, PCLCardTag.Unplayable, 1);
                   }
                });
    }
}