package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.SFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class CorrosionPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, CorrosionPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Purple);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 35);

    public CorrosionPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public static float calculateDamage(DamageInfo info, float multiplier)
    {
        float newDamage = Math.max(1, info.output * (multiplier / 100f));
        return GameUtilities.isPlayer(info.owner) ? Math.min(GameUtilities.getHP(info.owner, true, true) - 1, newDamage) : newDamage;
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return PCLEnum.AttackEffect.GHOST;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.DARKLING_REGROW_2, 0.95f, 1.05f);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
    {
        if (info.output > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL)
        {
            PCLActions.bottom.dealDamage(owner, owner, (int) calculateDamage(info, getIntensifyMultiplier()), DamageInfo.DamageType.THORNS, getAttackEffect());
            this.flash();
        }
    }
}
