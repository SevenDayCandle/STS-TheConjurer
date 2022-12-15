package pinacolada.cards.conjurer.series.shinmegamitensei;

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
import pinacolada.skills.skills.base.conditions.PCond_CheckOrb;
import pinacolada.skills.skills.base.modifiers.PMod_BonusPerAffinityLevel;

public class ShikiOuji extends PCLCard
{
    public static final PCLCardData DATA = register(ShikiOuji.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.AllEnemy)
            .setBlock(2, 1, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public ShikiOuji()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_BonusPerAffinityLevel(2, PCLAffinity.Red).setUpgrade(1), PMove.gainTemporary(7, PCLPowerHelper.Thorns));
        addUseMove(PCond.limited(), new PCond_CheckOrb(3, PCLOrbHelper.Air), PMove.gainTemporary(1, PCLPowerHelper.Buffer));
    }
}
