package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

@VisibleCard
public class Lucidity extends PCLCard {
    public static final PCLCardData DATA = register(Lucidity.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Blue.make(1, 1), PCLAffinity.Yellow.make())
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Lucidity() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(2, PCLPowerData.Warding).setUpgrade(2));
        addUseMove(new PMove_StabilizePower(PCLCardTarget.Team, PCLPowerData.Vigor, PCLPowerData.Warding));
    }
}
