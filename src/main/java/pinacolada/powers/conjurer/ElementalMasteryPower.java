package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.effects.PCLSFX;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;

@VisiblePower
public class ElementalMasteryPower extends PCLPower {
    public static final PCLPowerData DATA = register(ElementalMasteryPower.class, ConjurerResources.conjurer)
            .setType(PowerType.BUFF)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent)
            .setTooltip(ConjurerResources.conjurer.tooltips.elementalMastery);

    public ElementalMasteryPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);

    }

    @Override
    public void playApplyPowerSfx() {
        PCLActions.top.playSFX(PCLSFX.HEAL_3);
    }
}
