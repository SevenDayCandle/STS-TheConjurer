package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.base.modifiers.PMod_BonusPerAffinityLevel;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

public class Dagda extends PCLCard
{
    public static final PCLCardData DATA = register(Dagda.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(7, 1)
            .setBlock(7, 1)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Dagda()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove().setDamageEffect(PCLEffekseerEFX.BLOW08);
        addUseMove(new PMod_BonusPerAffinityLevel(2, PCLAffinity.Orange), new PMove_GainReaction(8).setUpgrade(2));
        addUseMove(PCond.checkLevel(2), new PMove_StabilizePower(PCLCardTarget.AllEnemy, 1, PCLPowerHelper.Stoned));
    }
}
