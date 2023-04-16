package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_GainGold;

@VisibleCard
public class TreasureChest extends PCLCard
{
    public static final PCLCardData DATA = register(TreasureChest.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.RARE)
            .setTags(PCLCardTag.Unplayable, PCLCardTag.Fleeting)
            .setObtainableInCombat(false)
            .setCore(true);

    public TreasureChest()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_GainGold(550).setUpgrade(200));
        addUseMove(PCond.onDraw(), PMove.createDiscardPile(1, TreasureChest_TreasureKey.DATA.ID));
    }
}