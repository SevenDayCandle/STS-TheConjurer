package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Lithosphere extends PCLCard {
    public static final PCLCardData DATA = register(Lithosphere.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(8, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public Lithosphere() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.applyToSingle(1, PetraPower.DATA).setUpgrade(1));
    }
}
