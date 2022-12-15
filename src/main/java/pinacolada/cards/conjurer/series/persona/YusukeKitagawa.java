package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;

public class YusukeKitagawa extends PCLCard
{
    public static final PCLCardData DATA = register(YusukeKitagawa.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setBlock(4, 2, 2)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.persona);

    public YusukeKitagawa()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.cooldown(2), PMove.selfTransform(YusukeKitagawa_Goemon.DATA));
        addUseMove(new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Chilled), PMove.gain(2, PCLPowerHelper.CounterAttack));
    }
}
