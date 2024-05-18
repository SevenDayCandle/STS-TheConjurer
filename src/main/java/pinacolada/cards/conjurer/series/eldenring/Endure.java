package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class Endure extends PCLCard {
    public static final PCLCardData DATA = register(Endure.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(7, 2)
            .setAffinities(1, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Endure() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PMod.perDistinctDebuff(PCLCardTarget.Self, 1), 3, 1);
    }
}
