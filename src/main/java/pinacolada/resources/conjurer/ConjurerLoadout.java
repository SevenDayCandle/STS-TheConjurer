package pinacolada.resources.conjurer;

import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.conjurer.basic.Defend;
import pinacolada.cards.conjurer.basic.Strike;
import pinacolada.resources.pcl.PCLLoadout;

public class ConjurerLoadout extends PCLLoadout
{
    public ConjurerLoadout(int series, int unlockLevel)
    {
        super(ConjurerEnum.Cards.THE_CONJURER, series, unlockLevel);
    }

    @Override
    public PCLCardData getDefend()
    {
        return Defend.DATA;
    }

    @Override
    public PCLCardData getStrike()
    {
        return Strike.DATA;
    }
}
