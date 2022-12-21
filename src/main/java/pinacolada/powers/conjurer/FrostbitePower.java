package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.CombatHelper;

public class FrostbitePower extends PCLPower implements HealthBarRenderPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FrostbitePower.class);
    public static final Color healthBarColor = Color.SKY.cpy();

    public FrostbitePower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        initialize(amount, PowerType.DEBUFF, false);
    }

    @Override
    public int getHealthBarAmount()
    {
        return CombatHelper.getHealthBarAmount(owner, amount, false, true);
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

        PCLActions.bottom.loseHP(source, owner, amount, AttackEffects.ICE)
                .canKill(owner == null || !owner.isPlayer);
        reducePower(1);
    }
}
