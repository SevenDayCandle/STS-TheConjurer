package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_EvokeOrb;

public class AyaShameimaru extends PCLCard
{
    public static final PCLCardData DATA = register(AyaShameimaru.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setBlock(4, 1, 2)
            .setUTags(PCLCardTag.Haste)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public AyaShameimaru()
    {
        super(DATA);
    }


    public void setup(Object input)
    {
        addUseMove(PCond.ifElse(PMove.gain(1, PCLPowerHelper.Blur), PMove.channelOrb(1, PCLOrbHelper.Air), new PCond_EvokeOrb(1, PCLOrbHelper.Air)));
        addUseMove(PCond.onExhaust(), PMove.draw(1).setAffinity(PCLAffinity.Green));
    }
}
