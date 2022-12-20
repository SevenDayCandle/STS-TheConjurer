/*
package pinacolada.events.pcl;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import eatyourbeets.events.base.EYBEventOption;
import eatyourbeets.events.base.EYBEventPhase;
import eatyourbeets.events.base.EYBEventStrings;
import pinacolada.cards.conjurer.basic.SheerCold;
import pinacolada.events.base.PCLEvent;
import pinacolada.effects.GameEffects;
import pinacolada.utilities.RandomizedList;

public class TheMysteriousPeak extends PCLEvent
{
    public static final EventStrings STRINGS = new EventStrings();
    public static final String ID = CreateFullID(TheMysteriousPeak.class);

    public TheMysteriousPeak()
    {
        super(ID, new EventStrings(), IMAGES.QingyunPeak.Path());

        RegisterPhase(0, new Introduction());
        RegisterPhase(1, new PhaseFight());
        RegisterPhase(2, new PhaseTrap());
        RegisterPhase(3, new Conclusion());
        RegisterPhase(4, new Flee());
        ProgressPhase();
    }

    private static class Introduction extends EYBEventPhase<TheMysteriousPeak, TheMysteriousPeak.EventStrings>
    {
        private final AbstractCard obtainedCard = new SheerCold();
        private final int HP_LOSS = player.currentHealth / 4;

        private void LoseHealth()
        {
            player.damage(new DamageInfo(null, HP_LOSS));
            GameEffects.List.Add(new ShowCardAndObtainEffect(obtainedCard, (float) Settings.WIDTH * 0.45f, (float) Settings.HEIGHT / 2f));
            ChangePhase(PhaseFight.class);
        }

        private void LoseRelic(EYBEventOption option)
        {
            player.relics.remove(option.relic);
            GameEffects.List.Add(new ShowCardAndObtainEffect(obtainedCard, (float) Settings.WIDTH * 0.45f, (float) Settings.HEIGHT / 2f));
            ChangePhase(PhaseTrap.class);
        }

        @Override
        protected void OnEnter()
        {
            RandomizedList<AbstractRelic> relics = new RandomizedList<>();

            for (AbstractRelic r : player.relics)
            {
                if (r.tier == AbstractRelic.RelicTier.COMMON)
                {
                    relics.Add(r);
                }
            }
            AbstractRelic relic = relics.Retrieve(RNG);
            if (relic == null)
            {
                for (AbstractRelic r : player.relics)
                {
                    if (r.tier == AbstractRelic.RelicTier.UNCOMMON)
                    {
                        relics.Add(r);
                    }
                }
                relic = relics.Retrieve(RNG);
            }

            AddText(text.Introduction());
            AddOption(text.FightOption(HP_LOSS), obtainedCard).AddCallback(this::LoseHealth);
            if (relic != null)
            {
                AddOption(text.TrapOption(relic.name), relic).AddCallback(this::LoseRelic);
            }
            else
            {
                AddOption(text.TrapLockedOption()).SetDisabled(true);
            }

            AddOption(text.FleeOption()).AddCallback(() -> ChangePhase(Flee.class));
        }
    }

    private static class PhaseFight extends EYBEventPhase<TheMysteriousPeak, TheMysteriousPeak.EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Fight());
            ChangePhase(3);
        }
    }

    private static class PhaseTrap extends EYBEventPhase<TheMysteriousPeak, TheMysteriousPeak.EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Trap());
            ChangePhase(3);
        }
    }

    private static class Conclusion extends EYBEventPhase<TheMysteriousPeak, TheMysteriousPeak.EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Conclusion());
            AddLeaveOption();
        }
    }

    private static class Flee extends EYBEventPhase<TheMysteriousPeak, TheMysteriousPeak.EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Flee());
            AddLeaveOption();
        }
    }

    private static class EventStrings extends EYBEventStrings
    {
        public final String Introduction()
        {
            return GetDescription(0);
        }

        public final String Fight()
        {
            return GetDescription(1);
        }

        public final String Trap()
        {
            return GetDescription(2);
        }

        public final String Conclusion()
        {
            return GetDescription(3);
        }

        public final String Flee()
        {
            return GetDescription(4);
        }

        public final String FightOption(int gold)
        {
            return GetOption(0, gold);
        }

        public final String TrapOption(String card)
        {
            return GetOption(1, card);
        }

        public final String TrapLockedOption()
        {
            return GetOption(2);
        }

        public final String FleeOption()
        {
            return GetOption(3);
        }
    }
}*/
