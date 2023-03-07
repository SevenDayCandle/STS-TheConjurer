package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.CombatHelper;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class BlastedPower extends PCLPower implements HealthBarRenderPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, BlastedPower.class);
    public static final Color healthBarColor = Color.FIREBRICK.cpy();

    public BlastedPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID);
        initialize(amount, PowerType.DEBUFF, true);
    }

    @Override
    public int getHealthBarAmount()
    {
        DamageInfo info = getEstimated();
        return CombatHelper.getHealthBarAmount(owner, info.output, false, true);
    }

    @Override
    public Color getColor()
    {
        return healthBarColor;
    }

    @Override
    public void atStartOfTurn()
    {
        this.flashWithoutSound();

        PCLActions.bottom.dealDamage(owner, getEstimated(), PCLAttackVFX.BURN).setPiercing(true, true);
        removePower();
    }

    public DamageInfo getEstimated()
    {
        float multiplier = 100;
        for (AbstractPower p : owner.powers) {
            for (ConjurerElementButton element : ConjurerReactionMeter.meter.getElementButtons())
            {
                if (element.canCombust(PCLAffinity.Red, p.ID))
                {
                    multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(PCLAffinity.Red);
                }
            }
        }
        int secondAmount = MathUtils.ceil(amount * multiplier / 100);
        DamageInfo estimated = new DamageInfo(owner, secondAmount, DamageInfo.DamageType.NORMAL);
        estimated.applyEnemyPowersOnly(owner);
        return estimated;
    }
}
