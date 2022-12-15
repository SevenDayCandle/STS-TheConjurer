/*
package pinacolada.events.pcl;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import eatyourbeets.events.base.EYBEventPhase;
import eatyourbeets.events.base.EYBEventStrings;
import pinacolada.events.base.PCLEvent;
import pinacolada.relics.conjurer.SoulGem1;
import pinacolada.relics.conjurer.SoulGem2;
import pinacolada.relics.conjurer.SoulGem3;
import pinacolada.relics.conjurer.SoulGem4;
import pinacolada.resources.PGR;
import pinacolada.utilities.GameUtilities;

public class ContractEvent extends PCLEvent
{
    public static final EventStrings STRINGS = new EventStrings();
    public static final String ID = CreateFullID(ContractEvent.class);

    public ContractEvent()
    {
        super(ID, new EventStrings(), IMAGES.Kyubey.Path());
        RegisterPhase(0, new Introduction());
        RegisterPhase(1, new MainPhase());
        RegisterPhase(2, new MainPhase2());
        RegisterPhase(3, new MainPhase3());
        RegisterPhase(4, new Ending1());
        RegisterPhase(5, new Ending2());
        RegisterPhase(6, new Ending3());
        ProgressPhase();
    }

    public static ContractEvent TryCreate(Random rng)
    {
        if (GameUtilities.HasEncounteredEvent(ID))
        {
            return null;
        }
        if (AbstractDungeon.floorNum > 20 && rng.randomBoolean(0.07f))
        {
            return new ContractEvent();
        }
        return null;
    }

    private static class Introduction extends EYBEventPhase<ContractEvent, EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Introduction());
            AddContinueOption();
        }
    }

    private static class MainPhase extends EYBEventPhase<ContractEvent, EventStrings>
    {

        @Override
        protected void OnEnter()
        {
            AddText(text.Choice1());
            AddOption(text.Accept()).AddCallback(() -> ChangePhase(MainPhase2.class));
            AddOption(text.Flee()).AddCallback(() -> ChangePhase(Ending2.class));
*/
/*            if (PCLUtils.Find(player.masterDeck.group, c -> Zhongli.DATA.ID.equals(c.cardID)) != null) {
                AddOption(text.Negotiate()).AddCallback(() -> ChangePhase(MainPhase3.class));
            }
            else {
                AddOption(text.NegotiateLocked()).SetDisabled(true);
            }*//*

        }
    }

    private static class MainPhase2 extends EYBEventPhase<ContractEvent, EventStrings>
    {

        private void AcceptRelic(AbstractRelic relic)
        {
            GameUtilities.ObtainRelicFromEvent(relic);
            ChangePhase(Ending1.class);
        }

        @Override
        protected void OnEnter()
        {
            SoulGem1 gem1 = new SoulGem1();
            SoulGem2 gem2 = new SoulGem2();
            SoulGem3 gem3 = new SoulGem3();
            AddText(text.Choice2());
            AddOption(text.AcceptRelic(), gem1).AddCallback((g) -> AcceptRelic(g.relic));
            AddOption(text.AcceptRelic(), gem2).AddCallback((g) -> AcceptRelic(g.relic));
            AddOption(text.AcceptRelic(), gem3).AddCallback((g) -> AcceptRelic(g.relic));
        }
    }

    private static class MainPhase3 extends EYBEventPhase<ContractEvent, EventStrings>
    {

        private void AcceptRelic(AbstractRelic relic)
        {
            GameUtilities.ObtainRelicFromEvent(relic);
            ChangePhase(Ending3.class);
        }

        @Override
        protected void OnEnter()
        {
            SoulGem4 gem1 = new SoulGem4();
            AddText(text.Choice2());
            AddOption(text.AcceptRelic(), gem1).AddCallback((g) -> AcceptRelic(g.relic));
            AddLeaveOption();
        }
    }

    private static class Ending1 extends EYBEventPhase<ContractEvent, EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Ending1());
            AddLeaveOption();
        }
    }

    private static class Ending2 extends EYBEventPhase<ContractEvent, EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Ending2());
            AddLeaveOption();
            PGR.PCL.Dungeon.SetJumpNextFloor(true);
        }
    }

    private static class Ending3 extends EYBEventPhase<ContractEvent, EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Ending3());
            AddLeaveOption();
        }
    }

    private static class EventStrings extends EYBEventStrings
    {
        public final String Accept()
        {
            return GetOption(0);
        }

        public final String AcceptRelic()
        {
            return GetOption(4);
        }

        public final String Choice1()
        {
            return GetDescription(1);
        }

        public final String Choice2()
        {
            return GetDescription(2);
        }

        public final String Choice3()
        {
            return GetDescription(3);
        }

        public final String Ending1()
        {
            return GetDescription(4);
        }

        public final String Ending2()
        {
            return GetDescription(5);
        }

        public final String Ending3()
        {
            return GetDescription(6);
        }

        public final String Flee()
        {
            return GetOption(1);
        }

        public final String Introduction()
        {
            return GetDescription(0);
        }

        public final String Negotiate()
        {
            return GetOption(2);
        }

        public final String NegotiateLocked()
        {
            return GetOption(3);
        }
    }
}*/
