package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class DefendR extends PCLCard {
    public static final PCLCardData DATA = register(DefendR.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.BASIC, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red)
            .setBlock(5, 3)
            .setDefend();

    public DefendR() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
    }
}