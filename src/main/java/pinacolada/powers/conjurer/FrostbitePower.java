package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.effects.SFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

public class FrostbitePower extends PCLPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FrostbitePower.class);
    public static final Color healthBarColor = Color.SKY.cpy();
    public boolean expanded;

    public FrostbitePower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        initialize(amount, PowerType.DEBUFF, true);
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ORB_FROST_DEFEND_1, 0.95f, 1.05f);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return super.atDamageGive(type == DamageInfo.DamageType.NORMAL ? damage - getPotency() : damage, type);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        return super.atDamageReceive((expanded || type == DamageInfo.DamageType.NORMAL) ? damage + getPotency(): damage, type);
    }

    @Override
    public float modifyOrbIncoming(float inital)
    {
        return expanded ? super.modifyOrbIncoming(inital) + getPotency() : super.modifyOrbIncoming(inital);
    }

    @Override
    public String getUpdatedDescription()
    {
        return formatDescription(0, PCLRenderHelpers.decimalFormat(getPotency()), getDecrease());
    }

    public float getPotency()
    {
        return this.amount / 10f;
    }

    @Override
    public void atEndOfRound()
    {
        super.atEndOfRound();
        reducePower(getDecrease());
        if (amount <= 0)
        {
            removePower();
        }
    }

    public int getDecrease()
    {
        return MathUtils.ceil(amount * 0.75f);
    }
}
