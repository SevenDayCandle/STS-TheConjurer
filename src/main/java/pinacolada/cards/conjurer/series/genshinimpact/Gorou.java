package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.base.modifiers.PMod_PerDistinctPower;

public class Gorou extends PCLCard
{
    public static final PCLCardData DATA = register(Gorou.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(4, 1)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Gorou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_PerDistinctPower(1).setTarget(PCLCardTarget.Single), PTrait.hasBlock(3).setUpgrade(1));
        addUseMove(PCond.combust(), PMove.applyToSingle(2, PCLPowerHelper.Weak));
    }
}
