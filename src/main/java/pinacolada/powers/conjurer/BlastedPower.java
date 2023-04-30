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
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

public class BlastedPower extends PCLPower implements HealthBarRenderPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, BlastedPower.class);
    public static final Color healthBarColor = Color.ORANGE.cpy();
    public boolean expanded;

    public BlastedPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(owner, source, POWER_ID);
        initialize(amount, PowerType.DEBUFF, true);
    }

    @Override
    public int getHealthBarAmount()
    {
        if (expanded)
        {
            DamageInfo info = getExpandedDamageInfo();
            return GameUtilities.getHealthBarAmount(owner, info.output, false, true);
        }
        return GameUtilities.getHealthBarAmount(owner, amount, false, true);
    }

    @Override
    public Color getColor() {
        return healthBarColor;
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, amount, getDecrease());
    }

    @Override
    public void atStartOfTurn() {
        this.flashWithoutSound();

        if (expanded) {
            for (AbstractCreature enemy : GameUtilities.getEnemies(true)) {
                DamageInfo info = getExpandedDamageInfo();
                PCLActions.bottom.dealDamage(owner, info, PCLAttackVFX.BURN).setPiercing(true, true);
            }
        }
        else {
            PCLActions.bottom.loseHP(source, owner, amount, PCLAttackVFX.BURN)
                    .canKill(owner == null || !owner.isPlayer);
        }

        reducePower(getDecrease());
        if (amount <= 0) {
            removePower();
        }
    }

    public int getDecrease() {
        return MathUtils.ceil(amount / 2f);
    }

    public DamageInfo getExpandedDamageInfo()
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
