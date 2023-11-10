package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class PowderSnow extends PCLCard {
    public static final PCLCardData DATA = register(PowderSnow.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setBlock(5, 2)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public PowderSnow() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.applyToSingle(1, AquaPower.DATA).setUpgrade(1));
    }
}
