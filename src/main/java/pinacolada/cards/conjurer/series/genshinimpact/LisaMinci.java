package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_ScryBranch;

public class LisaMinci extends PCLCard
{
    public static final PCLCardData DATA = register(LisaMinci.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setBlock(5, 1)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public LisaMinci()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_ScryBranch(2).setUpgrade(1).setAffinity(PCLAffinity.Green, PCLAffinity.Blue), PMultiSkill.join(
                PMove.gain(1, PCLPowerHelper.Energized),
                PMove.gain(1, PCLPowerHelper.Sorcery),
                PMove.gain(6, PCLPowerHelper.NextTurnBlock)
        ));
    }
}
