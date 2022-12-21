package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.interfaces.subscribers.OnOrbApplyLockOnSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.resources.conjurer.ConjurerResources;

public class ChilledPower extends AbstractPCLElementalPower implements OnOrbApplyLockOnSubscriber
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, ChilledPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Blue);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 25);

    public ChilledPower(AbstractCreature owner, AbstractCreature source, int amount)
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
        return AttackEffects.ICE;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ORB_FROST_CHANNEL, 0.95f, 1.05f);
    }

    @Override
    public void onInitialApplication()
    {
        super.onInitialApplication();

        CombatManager.onOrbApplyLockOn.subscribe(this);
    }

    @Override
    public void onRemove()
    {
        super.onRemove();

        CombatManager.onOrbApplyLockOn.unsubscribe(this);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        return super.atDamageReceive(type == DamageInfo.DamageType.NORMAL ? calculateDamage(damage, getIntensifyMultiplier()) : damage, type);
    }

    @Override
    public float onOrbApplyLockOn(AbstractCreature abstractCreature, float damage)
    {
        return calculateDamage(damage, getIntensifyMultiplier());
    }
}
