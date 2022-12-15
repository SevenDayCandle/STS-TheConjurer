package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.base.modifiers.PMod_BonusPerAffinityLevel;

public class Mothman extends PCLCard
{
    public static final PCLCardData DATA = register(Mothman.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setBlock(4, array(2, 0), 2, array(0,0))
            .setMultiformData(2)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Mothman()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_BonusPerAffinityLevel(4, PCLAffinity.Green), new PMove_GainReaction(4).setUpgrade(0, 1));
        addUseMove(PCond.delegate(PSkill.Delegate.Reshuffle), PMove.applyToRandom(1, PCLPowerHelper.Flowed, PCLPowerHelper.Poison).setUpgrade(0, 1));
    }
}
