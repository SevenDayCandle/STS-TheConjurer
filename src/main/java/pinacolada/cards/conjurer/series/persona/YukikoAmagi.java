package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class YukikoAmagi extends PCLCard
{
    public static final PCLCardData DATA = register(YukikoAmagi.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(6, 3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.persona);

    public YukikoAmagi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.channelOrb(2, PCLOrbHelper.Fire));
        addUseMove(PCond.cooldown(3), PMultiSkill.join(PMove.selfExhaust(), PMove.channelOrb(2, PCLOrbHelper.Fire)));
    }
}
