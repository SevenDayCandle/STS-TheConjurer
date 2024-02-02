package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.effects.PCLSFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PCLSubscribingPower;
import pinacolada.resources.conjurer.ConjurerResources;

@VisiblePower
public class ElementalMasteryPower extends PCLSubscribingPower {
    public static final PCLPowerData DATA = register(ElementalMasteryPower.class, ConjurerResources.conjurer)
            .setType(PowerType.BUFF)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent)
            .setTooltip(ConjurerResources.conjurer.tooltips.elementalMastery);

    public ElementalMasteryPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);

        if (power.amount > 0 && (power instanceof AbstractElementPower)) {
            power.amount += this.amount;

            final AbstractGameAction action = AbstractDungeon.actionManager.currentAction;
            if (action instanceof ApplyPowerAction) {
                action.amount += this.amount;
            }
            else {
                EUIUtils.logWarning(this, "Unknown action type: " + action.getClass().getName());
            }
        }
    }

    @Override
    public void playApplyPowerSfx() {
        PCLActions.top.playSFX(PCLSFX.HEAL_3);
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }
}
