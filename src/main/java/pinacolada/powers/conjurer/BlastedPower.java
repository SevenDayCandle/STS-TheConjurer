package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
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
    public int getHealthBarAmount() {
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
                PCLActions.bottom.loseHP(source, enemy, amount, PCLAttackVFX.BURN);
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
}
