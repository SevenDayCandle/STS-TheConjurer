package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;

public class StonedPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, StonedPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Orange);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 20);

    public StonedPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public static float calculateBlock(int damageAmount, float multiplier)
    {
        return Math.max(1, damageAmount * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return AttackEffects.EARTH;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.PCL_ORB_EARTH_CHANNEL, 0.95f, 1.05f);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (info.type == DamageInfo.DamageType.NORMAL && damageAmount > 0)
        {
            PCLActions.bottom.gain(PCLPowerHelper.NextTurnBlock, (int) calculateBlock(damageAmount, getIntensifyMultiplier()));
        }

        return super.onAttacked(info, damageAmount);
    }
}
