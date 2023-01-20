package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.actions.powers.ApplyPower;
import pinacolada.effects.SFX;
import pinacolada.interfaces.subscribers.OnTryGainResolveSubscriber;
import pinacolada.powers.PCLSubscribingPower;
import pinacolada.resources.conjurer.ConjurerResources;

public class ElementalMasteryPower extends PCLSubscribingPower implements OnTryGainResolveSubscriber
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, ElementalMasteryPower.class);

    public ElementalMasteryPower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);
        initialize(amount);
    }

    @Override
    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void reducePower(int reduceAmount)
    {
        super.reducePower(reduceAmount);
        updateDescription();
    }

    @Override
    public void playApplyPowerSfx()
    {
        PCLActions.top.playSFX(SFX.HEAL_3);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
    {
        super.onApplyPower(power, target, source);

        if (power.amount > 0 && (power instanceof AbstractPCLElementalPower))
        {
            power.amount += this.amount;

            final AbstractGameAction action = AbstractDungeon.actionManager.currentAction;
            if (action instanceof ApplyPower || action instanceof ApplyPowerAction)
            {
                action.amount += this.amount;
            }
            else
            {
                EUIUtils.logWarning(this, "Unknown action type: " + action.getClass().getName());
            }
        }
    }

    @Override
    public int onTryGainResolve(AbstractCard card, AbstractPlayer p, int originalCost, boolean isActuallyGaining, boolean isFromMatch)
    {
        if (originalCost > 0)
        {
            return originalCost + amount;
        }
        return originalCost;
    }

}
