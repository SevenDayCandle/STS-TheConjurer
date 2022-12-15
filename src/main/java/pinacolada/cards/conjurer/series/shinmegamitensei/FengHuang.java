package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PDelay;

public class FengHuang extends PCLCard
{
    public static final PCLCardData DATA = register(FengHuang.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON)
            .setBlock(6, 1, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public FengHuang()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PDelay(1), PMove.applyToEnemies(4, PCLPowerHelper.Burning).setUpgrade(2));
        addUseMove(PCond.onExhaust(), PMove.applyToEnemies(2, PCLPowerHelper.Weak));
    }
}
