package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.base.modifiers.PMod_PerDistinctPower;

public class GeothermalHeating extends PCLCard
{
    public static final PCLCardData DATA = register(GeothermalHeating.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(5, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setCore();

    public GeothermalHeating()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_PerDistinctPower(1).setTarget(PCLCardTarget.Single), PTrait.hasBlock(3).setUpgrade(1));
    }
}