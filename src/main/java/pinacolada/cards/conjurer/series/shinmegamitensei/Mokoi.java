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
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.base.conditions.PCond_CheckPower;

public class Mokoi extends PCLCard
{
    public static final PCLCardData DATA = register(Mokoi.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(2, 1)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Mokoi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.ifElse(PMove.applyToSingle(2, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak, PCLPowerHelper.Shackles).setUpgrade(1), new PMove_GainReaction(5).setUpgrade(1), new PCond_CheckPower(PCLCardTarget.Single, 0)));
        addUseMove(PCond.combust(), new PMove_GainReaction(4));
    }
}
