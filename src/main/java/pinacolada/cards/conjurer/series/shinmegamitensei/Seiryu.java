package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.conditions.PCond_CheckOrb;

public class Seiryu extends PCLCard
{
    public static final PCLCardData DATA = register(Seiryu.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setBlock(5, 3)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Seiryu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMultiSkill.choose(PMove.channelOrb(2, PCLOrbHelper.Air), PMove.channelOrb(2, PCLOrbHelper.Earth)));
        addUseMove(PCond.limited(), new PCond_CheckOrb(3, PCLOrbHelper.Earth), PMove.gainOrbSlots(1));
    }
}
