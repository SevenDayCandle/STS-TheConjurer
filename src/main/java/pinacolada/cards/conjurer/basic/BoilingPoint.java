package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class BoilingPoint extends PCLCard {
    public static final PCLCardData DATA = register(BoilingPoint.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public BoilingPoint() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.checkPower(PCLCardTarget.Any, 1), PMove.applyToRandom(1, IgnisPower.DATA).setUpgrade(1)));
    }
}
