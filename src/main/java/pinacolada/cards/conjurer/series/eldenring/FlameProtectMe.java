package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class FlameProtectMe extends PCLCard {
    public static final PCLCardData DATA = register(FlameProtectMe.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Team)
            .setBlock(6, 3)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FlameProtectMe() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PMod.perPowerAny(1, IgnisPower.DATA), 2);
    }
}
