package pinacolada.cards.conjurer.colorless;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.interfaces.subscribers.OnBattleEndSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.CardSelection;
import pinacolada.utilities.WeightedList;

public class Rorona extends PCLCard
{
    public static final PCLCardData DATA = register(Rorona.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setColorless()
            .setMaxCopies(1)
            .setObtainableInCombat(false);

    public Rorona()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.gain(2, PCLPowerHelper.Vitality).setUpgrade(2), getSpecialMove(0, this::action, 1));
        addGainPower((PTrigger) PTrigger.interactablePerCombat(2, PCond.payEnergy(2), getSpecialMove(1, this::specialMove, 1)));
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.applyPower(new RoronaPower(info.source, move));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.add(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(false)));
    }

    public static class RoronaPower extends PSpecialCardPower implements OnBattleEndSubscriber
    {
        private final WeightedList<AbstractCard> toUpgrade = new WeightedList<>();

        public RoronaPower(AbstractCreature owner, PSkill move)
        {
            super(Rorona.DATA, owner, move);
        }

        @Override
        public void onBattleEnd()
        {
            toUpgrade.clear();
            PCLActions.instant.upgradeFromPile(name, amount, player.masterDeck)
                    .setFilter(c -> !c.cardID.equals(Rorona.DATA.ID))
                    .setOptions(CardSelection.special(null, (list, index, remove) ->
                    {
                        if (toUpgrade.size() == 0)
                        {
                            for (AbstractCard c : list)
                            {
                                switch (c.rarity)
                                {
                                    case BASIC:
                                        toUpgrade.add(c, 60);
                                        break;
                                    case COMMON:
                                        toUpgrade.add(c, 50);
                                        break;
                                    case UNCOMMON:
                                        toUpgrade.add(c, 25);
                                        break;
                                    case RARE:
                                        toUpgrade.add(c, 10);
                                        break;
                                    default:
                                        toUpgrade.add(c, 1);
                                        break;
                                }
                            }
                        }
                        return toUpgrade.retrieve(rng);
                    }), true);

            flash();
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            CombatManager.onBattleEnd.subscribe(this);
        }

        @Override
        public void onRemove()
        {
            super.onRemove();

            CombatManager.onBattleEnd.unsubscribe(this);
        }
    }
}
