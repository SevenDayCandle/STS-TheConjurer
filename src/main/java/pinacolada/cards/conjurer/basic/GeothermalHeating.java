package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class GeothermalHeating extends PCLCard {
    public static final PCLCardData DATA = register(GeothermalHeating.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(6, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setCore();

    public GeothermalHeating() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove().setBonus(PMod.perDistinctDebuff(PCLCardTarget.All, 1), 1, 0);
    }
}
