package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class DefendV extends PCLCard {
    public static final PCLCardData DATA = register(DefendV.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.BASIC, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Green)
            .setBlock(5, 3)
            .setDefend()
            .setCore();

    public DefendV() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
    }
}