package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_BonusPerAffinityLevel;

public class SakuyaIzayoi extends PCLCard
{
    public static final PCLCardData DATA = register(SakuyaIzayoi.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setBlock(5, array(3, -1))
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SakuyaIzayoi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_BonusPerAffinityLevel(1, PCLAffinity.Green), PMove.cycle(2));
        addUseMove(PCond.combust(), new PMove_ObtainThrowingKnife(1));
    }
}
