package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.rooms.*;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

public class BountyMap extends AbstractBountyMap {
    public static final PCLRelicData DATA = register(BountyMap.class, ConjurerResources.conjurer).setProps(RelicTier.SPECIAL, LandingSound.FLAT);

    public BountyMap() {
        super(DATA);
    }

    @Override
    protected Class<? extends AbstractRoom> getCurrentRequiredRoom() {
        switch (counter) {
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