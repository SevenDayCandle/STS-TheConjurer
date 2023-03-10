package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.rooms.*;

public class BountyMap extends AbstractBountyMap
{
    public static final String ID = createFullID(BountyMap.class);

    public BountyMap()
    {
        super(ID);
    }

    @Override
    protected Class<? extends AbstractRoom> getCurrentRequiredRoom()
    {
        switch (counter)
        {
            case 0:
                return MonsterRoom.class;
            case 1:
                return MonsterRoomElite.class;
            case 2:
                return TreasureRoom.class;
            case 3:
                return ShopRoom.class;
            default:
                return EventRoom.class;
        }
    }

/*    @Override
    protected AnimatorCustomEventRoom.GetEvent GetEventConstructor()
    {
        return TheMysteriousPeak::new;
    }*/
}