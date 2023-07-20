package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.CMove;

@VisibleCard
public class FlashSweat extends PCLCard {
    public static final PCLCardData DATA = register(FlashSweat.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(3, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FlashSweat() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(CCond.react(), CMove.gainMatter(7).setUpgrade(2));
    }
}
