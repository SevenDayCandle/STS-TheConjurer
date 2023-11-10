package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class CollapsingSky extends PCLCard {
    public static final PCLCardData DATA = register(CollapsingSky.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.honkai);

    public CollapsingSky() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(2, PCond.blockBreak(PCLCardTarget.Any), PMove.applyToTeam(2, PCLPowerData.Vigor)).setUpgrade(1));
    }
}
