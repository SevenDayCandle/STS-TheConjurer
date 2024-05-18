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
public class ShieldCrash extends PCLCard {
    public static final PCLCardData DATA = register(ShieldCrash.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setBlock(8, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ShieldCrash() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PMod.perDistinctPower(PCLCardTarget.Self, 1), 1, 1);
    }
}
