package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.effects.SFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class FrostbitePower extends PCLPower implements HealthBarRenderPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FrostbitePower.class);
    public static final Color healthBarColor = Color.SKY.cpy();

    public FrostbitePower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        initialize(amount, PowerType.DEBUFF, true);
    }

    @Override
    public int getHealthBarAmount()
    {
        return GameUtilities.getHealthBarAmount(owner, amount, false, true);
    }

    @Override
    public Color getColor()
    {
        return healthBarColor;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ORB_FROST_DEFEND_1, 0.95f, 1.05f);
    }

    @Override
    public void atStartOfTurn()
    {
        this.flashWithoutSound();

        PCLActions.bottom.loseHP(source, owner, amount, PCLAttackVFX.ICE)
                .canKill(owner == null || !owner.isPlayer);
        reducePower(MathUtils.ceil(amount / 2f));
        if (amount <= 0)
        {
            removePower();
        }
    }
}
