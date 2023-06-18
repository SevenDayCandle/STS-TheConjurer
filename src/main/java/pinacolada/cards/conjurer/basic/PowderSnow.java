package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class PowderSnow extends PCLCard {
    public static final PCLCardData DATA = register(PowderSnow.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setBlock(2, 2)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public PowderSnow() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.applyToSingle(3, PCLElementHelper.Aqua).setUpgrade(1));
    }
}
