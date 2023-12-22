package pinacolada.cards.conjurer.colorless;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class TreasureChest_TreasureKey extends PCLCard {
    public static final PCLCardData DATA = register(TreasureChest_TreasureKey.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL)
            .setTags(PCLCardTag.Purge.make(1))
            .setCore(true);

    public TreasureChest_TreasureKey() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.draw(1).setUpgrade(1));
        addUseMove(PMove.modifyTag(0, 1, PCLCardTag.Unplayable).edit(f -> f.setCardIDs(TreasureChest.DATA.ID)));
    }
}