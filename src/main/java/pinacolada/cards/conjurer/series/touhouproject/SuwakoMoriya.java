package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.base.conditions.PCond_CheckPower;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;

public class SuwakoMoriya extends PCLCard
{
    public static final PCLCardData DATA = register(SuwakoMoriya.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(5, 2)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SuwakoMoriya()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.ifElse(PMove.applyToSingle(2, PCLPowerHelper.Weak), PMove.applyToSingle(5, PCLPowerHelper.Poison).setUpgrade(1), new PCond_CheckPower(PCLCardTarget.Single, 1, PCLPowerHelper.Poison)));
        addUseMove(PCond.combust(), new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Flowed), PTrait.hasBlock(1));
    }
}
