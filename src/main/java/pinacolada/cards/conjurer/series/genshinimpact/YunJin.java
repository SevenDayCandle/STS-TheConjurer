package pinacolada.cards.conjurer.series.genshinimpact;


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
import pinacolada.skills.skills.base.modifiers.PMod_CyclePerCard;

public class YunJin extends PCLCard
{
    public static final PCLCardData DATA = register(YunJin.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setBlock(7, 3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public YunJin()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_CyclePerCard(2), PMove.applyToRandom(2, PCLPowerHelper.Stoned));
        addUseMove(PCond.delegate(PSkill.Delegate.Discard, PSkill.Delegate.Reshuffle), PMove.retain(1));
    }
}
