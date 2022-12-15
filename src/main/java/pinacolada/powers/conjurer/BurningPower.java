package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.resources.conjurer.ConjurerResources;

public class BurningPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, BurningPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Red);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 30);

    public BurningPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public static float calculateDamage(float damage, float multiplier)
    {
        return damage + Math.max(1, damage * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return AttackEffects.BURN;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ATTACK_FIRE, 0.95f, 1.05f);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        return super.atDamageReceive(type == DamageInfo.DamageType.NORMAL ? calculateDamage(damage, getIntensifyMultiplier()) : damage, type);
    }
}