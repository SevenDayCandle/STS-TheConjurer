package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

public class KaeyaAlberich extends PCLCard
{
    public static final PCLCardData DATA = register(KaeyaAlberich.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Single)
            .setBlock(4, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KaeyaAlberich()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.channelOrb(1, PCLOrbHelper.Water));
        addUseMove(PMultiCond.or(PCond.combust(), PCond.redox()), PMove.triggerOrb(1, 1));
    }
}
