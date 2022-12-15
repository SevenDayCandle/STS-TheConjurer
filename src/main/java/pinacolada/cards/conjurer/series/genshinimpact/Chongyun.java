package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_BonusPerAffinityLevel;

public class Chongyun extends PCLCard
{
    public static final PCLCardData DATA = register(Chongyun.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Single)
            .setBlock(7, 2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Chongyun()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_BonusPerAffinityLevel(1, PCLAffinity.Blue), PMove.applyToSingle(4, PCLPowerHelper.Chilled).setUpgrade(1));
        addUseMove(PCond.combust(), PMove.channelOrb(1, PCLOrbHelper.Water));
    }
}
