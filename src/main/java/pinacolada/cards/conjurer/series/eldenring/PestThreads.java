package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class PestThreads extends PCLCard {
    public static final PCLCardData DATA = register(PestThreads.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.RandomEnemy)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public PestThreads() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMod.drawPer(2).setUpgrade(1).edit(f -> f.setAffinity(PCLAffinity.Green)),
                PMove.applyToRandom(5, PCLPowerData.Poison));
    }
}
