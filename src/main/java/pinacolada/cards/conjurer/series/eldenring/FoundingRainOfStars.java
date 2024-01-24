package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class FoundingRainOfStars extends PCLCard {
    public static final PCLCardData DATA = register(FoundingRainOfStars.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue.make(2), PCLAffinity.Yellow.make())
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FoundingRainOfStars() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(PTrigger.when(PCond.onOtherCardPlayed(PCLAffinity.Blue), PMove.applyToRandom(1, AquaPower.DATA).setUpgrade(1)));
    }
}
