package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_React;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Sublimation extends PCLCard {
    public static final PCLCardData DATA = register(Sublimation.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public Sublimation() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(new PCond_React(), PMove.loseHp(PCLCardTarget.AllEnemy, 2).setUpgrade(1)));
    }
}
