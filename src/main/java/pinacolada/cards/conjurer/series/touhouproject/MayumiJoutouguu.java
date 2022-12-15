package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class MayumiJoutouguu extends PCLCard
{
    public static final PCLCardData DATA = register(MayumiJoutouguu.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(7, 3)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public MayumiJoutouguu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.discard(1, PCLCardGroupHelper.Hand), PMove.channelOrb(1, PCLOrbHelper.Earth));
        addUseMove(PCond.semiLimited(), PCond.redox(), PMove.gain(2, PCLPowerHelper.PlatedArmor));
    }
}
