package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.annotations.VisiblePower;
import pinacolada.effects.PCLSFX;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

@VisiblePower
public class CooledPower extends PCLPower {
    public static final PCLPowerData DATA = register(CooledPower.class, ConjurerResources.conjurer)
            .setType(PowerType.DEBUFF)
            .setEndTurnBehavior(PCLPowerData.Behavior.SingleTurn)
            .setTooltip(ConjurerResources.conjurer.tooltips.cooled)
            .setPriority(3);
    public static final int POTENCY = 2;
    public CooledPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return super.atDamageGive(type == DamageInfo.DamageType.NORMAL ? damage - getPotency() : damage, type);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return super.atDamageReceive(type == DamageInfo.DamageType.NORMAL ? damage + getPotency() : damage, type, card);
    }

    public float getPotency() {
        return this.amount / (float) POTENCY;
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getPotency()));
    }

    @Override
    public float modifyOrbIncoming(float inital) {
        return super.modifyOrbIncoming(inital) + getPotency();
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ORB_FROST_DEFEND_1, 0.95f, 1.05f);
    }
}
