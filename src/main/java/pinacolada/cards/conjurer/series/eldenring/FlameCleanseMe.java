package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class FlameCleanseMe extends PCLCard {
    public static final PCLCardData DATA = register(FlameCleanseMe.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FlameCleanseMe() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gain(-1, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak).setUpgrade(-1));
        addUseMove(PMove.applyToEveryone(3, PCLElementHelper.Ignis).setUpgrade(1));
    }
}
