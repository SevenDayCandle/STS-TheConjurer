package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class BraggartsRoar extends PCLCard {
    public static final PCLCardData DATA = register(BraggartsRoar.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BraggartsRoar() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(12, PCLPowerData.Vigor).setUpgrade(4));
        addUseMove(PMove.applyToRandom(4, PCLPowerData.Vigor));
    }
}
