package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class FlashSweat extends PCLCard {
    public static final PCLCardData DATA = register(FlashSweat.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON, PCLCardTarget.SelfSingle)
            .setBlock(4, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public FlashSweat() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.apply(PCLCardTarget.SelfSingle, 2, AquaPower.DATA));
    }
}
