package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import extendedui.EUIUtils;
import extendedui.ui.tooltips.EUITooltip;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;

public abstract class AbstractBountyMap extends PCLRelic {
    public AbstractBountyMap(PCLRelicData data) {
        super(data);
    }

    @Override
    public void update() {
        super.update();
        if (counter < 0) {
            this.usedUp();
        }
    }    protected Class<? extends AbstractRoom> getCurrentRequiredRoom() {
        return EventRoom.class;
    }

    /*    protected abstract AnimatorCustomEventRoom.GetEvent GetEventConstructor();*/

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        super.justEnteredRoom(room);

        Class<? extends AbstractRoom> roomType = this.getCurrentRequiredRoom();

        if (counter >= 0 && room != null && (room.getClass().equals(roomType) || (roomType.equals(EventRoom.class) && "?".equals(room.getMapSymbol())))) {
            flash();
            if (roomType.equals(EventRoom.class)) {
                setCounter(-1);
                this.usedUp();
                /*PCLEvent.ForceEvent(GetEventConstructor());*/
            }
            else {
                addCounter(1);
            }
            fixDescription();
        }
    }

    private void fixDescription() {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new EUITooltip(this.getName(), this.description));
        this.initializeTips();
    }

    @Override
    public String getUpdatedDescription() {
        Class<? extends AbstractRoom> room = this.getCurrentRequiredRoom();
        if (CardCrawlGame.isInARun() && counter >= 0) {
            String name = room.equals(MonsterRoomElite.class) ? "Elite" : room.getSimpleName().split("Room")[0];
            return EUIUtils.format(DESCRIPTIONS[0], DESCRIPTIONS[1] + name);
        }
        else {
            return EUIUtils.format(DESCRIPTIONS[0], "");
        }
    }

    @Override
    public void onEquip() {
        super.onEquip();
        setCounter(0);

        fixDescription();
    }




}