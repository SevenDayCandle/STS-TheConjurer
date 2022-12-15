package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.*;
import pinacolada.cards.pcl.replacement.Miracle;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class SanaeKochiya extends PCLCard
{
    public static final PCLCardData DATA = register(SanaeKochiya.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(4, array(3, 1))
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SanaeKochiya()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.exhaust(1, PCLCardGroupHelper.Hand), PMove.obtain(Miracle.DATA));
        addUseMove(PCond.combust(), PMove.gainTemporary(1, PCLPowerHelper.Artifact));
    }
}
