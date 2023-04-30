package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.rooms.*;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

public class BountyMap2 extends AbstractBountyMap {
    public static final PCLRelicData DATA = register(BountyMap2.class, ConjurerResources.conjurer).setTier(RelicTier.SPECIAL, LandingSound.FLAT);

    public BountyMap2() {
        super(DATA);
    }

    @Override
    public Class<? extends AbstractRoom> getCurrentRequiredRoom() {
        switch (counter) {
            case 0:
                return MonsterRoom.class;
            case 1:
                return ShopRoom.class;
            case 2:
                return RestRoom.class;
            case 3:
                return MonsterRoomElite.class;
            default:
                return EventRoom.class;
        }
    }

/*    @Override
    protected AnimatorCustomEventRoom.GetEvent GetEventConstructor()
    {
        return TheSecludedHarbor::new;
    }*/
}