package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class FlowedPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FlowedPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Green);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 20);

    public FlowedPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public static float calculateDamage(int damageAmount, float multiplier)
    {
        return Math.max(1, damageAmount * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return AttackEffects.WIND;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.WIND, 0.75f, 0.85f);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0)
        {
            if (GameUtilities.isPlayer(owner))
            {
                PCLActions.bottom.dealDamage(owner, owner, (int) calculateDamage(damageAmount, getIntensifyMultiplier()), DamageInfo.DamageType.THORNS, AttackEffects.WIND);
            }
            else
            {
                for (AbstractMonster enemy : GameUtilities.getEnemies(true))
                {
                    PCLActions.bottom.dealDamage(owner, enemy, (int) calculateDamage(damageAmount, getIntensifyMultiplier()), DamageInfo.DamageType.THORNS, AttackEffects.WIND);
                }
            }
        }

        return super.onAttacked(info, damageAmount);
    }
}
