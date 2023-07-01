package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.effects.PCLSFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

public class FrostbitePower extends PCLPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FrostbitePower.class);
    public static final float POTENCY = 10;
    public static final Color healthBarColor = Color.SKY.cpy();
    public float decay = 0.75f;
    public boolean expanded;

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(owner, POWER_ID);
        this.priority = 0;
        initialize(amount, PowerType.DEBUFF, true);
    }

    public int getDecrease() {
        return MathUtils.ceil(amount * decay);
    }

    public float getPotency() {
        return this.amount / POTENCY;
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getPotency()), getDecrease());
    }

    @Override
    public float modifyOrbIncoming(float inital) {
        return super.modifyOrbIncoming(inital) + getPotency();
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ORB_FROST_DEFEND_1, 0.95f, 1.05f);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return super.atDamageGive(type == DamageInfo.DamageType.NORMAL ? damage - getPotency() : damage, type);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return super.atDamageReceive((expanded || type == DamageInfo.DamageType.NORMAL) ? damage + getPotency() : damage, type);
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
        reducePower(getDecrease());
        if (amount <= 0) {
            removePower();
        }
    }
}
