package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
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
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);

        if (isPlayer) {
            doEffect();
        }
    }

    @Override
    public void atStartOfTurn() {
        doEffect();
    }

    @Override
    public Color getColor() {
        return healthBarColor;
    }

    public DamageInfo getExpandedDamageInfo() {
        float multiplier = 100;
        for (AbstractPower p : owner.powers) {
            for (ConjurerElementButton element : ConjurerReactionMeter.meter.getElementButtons()) {
                if (element.canReact(PCLAffinity.Red, p.ID)) {
                    multiplier += AbstractPCLElementalPower.getAmplifyMultiplier(PCLAffinity.Red);
                }
            }
        }
        int secondAmount = MathUtils.ceil(amount * multiplier / 100);
        DamageInfo estimated = new DamageInfo(owner, secondAmount, DamageInfo.DamageType.NORMAL);
        estimated.applyEnemyPowersOnly(owner);
        return estimated;
    }

    @Override
    public int getHealthBarAmount() {
        if (expanded) {
            DamageInfo info = getExpandedDamageInfo();
            return GameUtilities.getHealthBarAmount(owner, info.output, true, true);
        }
        return GameUtilities.getHealthBarAmount(owner, amount, true, true);
    }

    protected void doEffect() {
        this.flashWithoutSound();

        if (expanded) {
            for (AbstractCreature enemy : GameUtilities.getEnemies(true)) {
                DamageInfo info = getExpandedDamageInfo();
                PCLActions.bottom.dealDamage(owner, info, PCLAttackVFX.FIRE).setPiercing(true, false);
            }
        }
        else {
            PCLActions.bottom.dealDamage(source, owner, amount, DamageInfo.DamageType.THORNS, PCLAttackVFX.FIRE)
                    .canKill(owner == null || !owner.isPlayer);
        }

        removePower();
    }
}
