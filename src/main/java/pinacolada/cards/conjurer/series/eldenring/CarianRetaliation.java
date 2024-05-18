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
import pinacolada.skills.skills.base.modifiers.PMod_PerCreatureDamage;

@VisibleCard
public class CarianRetaliation extends PCLCard {
    public static final PCLCardData DATA = register(CarianRetaliation.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setBlock(7, 3)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CarianRetaliation() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(new PMod_PerCreatureDamage(PCLCardTarget.Single, 2), PMove.gain(1, PCLPowerData.Vigor));
    }
}
