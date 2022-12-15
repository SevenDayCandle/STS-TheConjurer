/*
package pinacolada.events.pcl;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import eatyourbeets.cards.base.modifiers.BlockModifiers;
import eatyourbeets.cards.base.modifiers.CostModifiers;
import eatyourbeets.cards.base.modifiers.DamageModifiers;
import eatyourbeets.events.base.EYBEventPhase;
import eatyourbeets.events.base.EYBEventStrings;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.resources.decider.curse.Curse_TimeParadox;
import pinacolada.events.base.PCLEvent;
import pinacolada.effects.GameEffects;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLUtils;
import pinacolada.utilities.RandomizedList;

public class MicrowaveEvent extends PCLEvent
{
    public static final EventStrings STRINGS = new EventStrings();
    public static final String ID = CreateFullID(MicrowaveEvent.class);

    public MicrowaveEvent()
    {
        super(ID, new EventStrings(), IMAGES.Microwave.Path());
        RegisterPhase(0, new MainPhase());
        RegisterPhase(1, new Eaten());
        RegisterPhase(2, new Cooked());
        ProgressPhase();
    }

    public static MicrowaveEvent TryCreate(Random rng)
    {
        if (GameUtilities.HasEncounteredEvent(ID))
        {
            return null;
        }
        if (AbstractDungeon.floorNum > 5 && rng.randomBoolean(0.1f))
        {
            return new MicrowaveEvent();
        }
        return null;
    }

    private static class MainPhase extends EYBEventPhase<MicrowaveEvent, EventStrings>
    {
        private final int HEAL_AMOUNT = GetMaxHP(12);
        private final int HEAL_AMOUNT2 = GetMaxHP(15);

        private void Cook()
        {
            GameEffects.List.Callback(new WaitAction(Settings.FAST_MODE ? 0.5f : 3f), () -> {
                player.heal(HEAL_AMOUNT2);
                RandomizedList<AbstractCard> toRemove = new RandomizedList<>();
                for (AbstractCard card : player.masterDeck.group)
                {
                    if (card.type == AbstractCard.CardType.CURSE && GameUtilities.CanRemoveFromDeck(card))
                    {
                        toRemove.Add(card);
                    }
                }

                if (toRemove.Size() > 0)
                {
                    RemoveCard(toRemove.Retrieve(RNG), true);
                    GameEffects.Queue.Add(new ShowCardAndObtainEffect(new Curse_TimeParadox(), (float) Settings.WIDTH * 0.45f, (float) Settings.HEIGHT / 2f));
                }
                else
                {
                    for (AbstractCard card : player.masterDeck.group)
                    {
                        if (card.type != AbstractCard.CardType.STATUS && GameUtilities.CanRemoveFromDeck(card) && card instanceof PCLCard)
                        {
                            toRemove.Add(card);
                        }

                    }
                    if (toRemove.Size() > 0)
                    {
                        PCLCard eC = PCLUtils.SafeCast(toRemove.Retrieve(RNG), PCLCard.class);
                        if (eC != null)
                        {
                            if (eC.GetMaxForms() > 1)
                            {
                                eC.ChangeForm(MathUtils.random(0, eC.cardData.MaxForms - 1), eC.timesUpgraded);
                            }
                            if (eC.baseDamage > 0)
                            {
                                int change = MathUtils.random(-2, 2);
                                DamageModifiers.For(eC).Add(ID, change);
                                eC.auxiliaryData.modifiedDamage += change;
                            }
                            if (eC.baseBlock > 0)
                            {
                                int change = MathUtils.random(-2, 2);
                                BlockModifiers.For(eC).Add(ID, change);
                                eC.auxiliaryData.modifiedBlock += change;
                            }
                            if (eC.cost >= 0)
                            {
                                int change = MathUtils.random(-1, 1);
                                CostModifiers.For(eC).Add(ID, change);
                                eC.auxiliaryData.modifiedCost += change;
                            }
                            GameEffects.Queue.ShowCardBriefly(eC.makeStatEquivalentCopy());
                        }
                    }
                }

                ChangePhase(Cooked.class);
            });
        }

        private void Eat()
        {
            player.heal(HEAL_AMOUNT);
            ChangePhase(Eaten.class);
        }

        @Override
        protected void OnEnter()
        {
            AddText(text.MainPhase());
            AddOption(text.EatOption(HEAL_AMOUNT)).AddCallback(this::Eat);
            AddOption(text.CookOption(HEAL_AMOUNT2)).AddCallback(this::Cook);
        }
    }

    private static class Eaten extends EYBEventPhase<MicrowaveEvent, EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Eaten());
            AddLeaveOption();
        }
    }

    private static class Cooked extends EYBEventPhase<MicrowaveEvent, EventStrings>
    {
        @Override
        protected void OnEnter()
        {
            AddText(text.Cooked());
            AddLeaveOption();
        }
    }

    private static class EventStrings extends EYBEventStrings
    {
        public final String CookOption(int heal)
        {
            return GetOption(1, heal);
        }

        public final String Cooked()
        {
            return GetDescription(2);
        }

        public final String EatOption(int heal)
        {
            return GetOption(0, heal);
        }

        public final String Eaten()
        {
            return GetDescription(1);
        }

        public final String MainPhase()
        {
            return GetDescription(0);
        }
    }
}*/
