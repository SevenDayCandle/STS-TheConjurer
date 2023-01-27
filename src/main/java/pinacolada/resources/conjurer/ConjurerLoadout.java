package pinacolada.resources.conjurer;

import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.conjurer.basic.*;
import pinacolada.resources.loadout.PCLCardSlot;
import pinacolada.resources.loadout.PCLLoadout;

public class ConjurerLoadout extends PCLLoadout
{
    public ConjurerLoadout(int series, int unlockLevel)
    {
        super(ConjurerEnum.Cards.THE_CONJURER, series, unlockLevel);
    }

    @Override
    public void addBasicDefends(PCLCardSlot slot) {
        super.addBasicDefends(slot);
        slot.addItem(DefendG.DATA, -1);
        slot.addItem(DefendO.DATA, -1);
    }

    @Override
    public void addBasicStrikes(PCLCardSlot slot) {
        super.addBasicStrikes(slot);
        slot.addItem(StrikeB.DATA, -1);
        slot.addItem(StrikeR.DATA, -1);
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
