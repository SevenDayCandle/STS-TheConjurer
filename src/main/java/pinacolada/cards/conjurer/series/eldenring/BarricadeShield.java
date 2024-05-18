package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;

@VisibleCard
public class BarricadeShield extends PCLCard {
    public static final PCLCardData DATA = register(BarricadeShield.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(12, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public BarricadeShield() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setChain(PMod.perCard(1, PCLCardGroupHelper.Hand), PTrait.block(3).setUpgrade(1));
    }
}
