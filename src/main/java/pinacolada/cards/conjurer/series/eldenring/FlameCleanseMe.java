package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class FlameCleanseMe extends PCLCard {
    public static final PCLCardData DATA = register(FlameCleanseMe.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FlameCleanseMe() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToTeam(-1, PCLPowerData.Vulnerable, PCLPowerData.Weak).setUpgrade(-1));
        addUseMove(PMove.applyToEnemies(2, IgnisPower.DATA).setUpgrade(1));
    }
}
