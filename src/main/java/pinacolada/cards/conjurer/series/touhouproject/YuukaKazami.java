package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class YuukaKazami extends PCLCard
{
    public static final PCLCardData DATA = register(YuukaKazami.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setBlock(8, array(3, -2))
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public YuukaKazami()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.triggerOrb(1, 2).setAlt(true).setUpgrade(0, 1));
        addUseMove(PCond.redox().setAlt(true), PMove.channelOrb(1, PCLOrbHelper.Air, PCLOrbHelper.Water).setAlt(true));
    }
}
