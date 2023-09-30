package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisiblePower
public class BlastedPower extends PCLPower implements HealthBarRenderPower {
    public static final PCLPowerData DATA = register(BlastedPower.class, ConjurerResources.conjurer)
            .setType(PowerType.DEBUFF)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent)
            .setTooltip(ConjurerResources.conjurer.tooltips.blasted)
            .setPriority(3);
    public static final Color healthBarColor = Color.ORANGE.cpy();
    public static final int DECAY = 50;
    public boolean expanded;

    public BlastedPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
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
        if (!owner.isPlayer) {
            doEffect();
        }
    }

    @Override
    public Color getColor() {
        return healthBarColor;
    }

    public int getDecrease() {
        return MathUtils.ceil(amount * DECAY / 100f);
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

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, amount, getDecrease());
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

        reducePower(getDecrease());
        if (amount <= 0) {
            removePower();
        }
    }
}
