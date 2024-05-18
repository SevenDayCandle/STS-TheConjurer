package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class DefendA extends PCLCard {
    public static final PCLCardData DATA = register(DefendA.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.BASIC, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Blue)
            .setBlock(5, 3)
            .setDefend()
            .setCore();

    public DefendA() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
    }
}