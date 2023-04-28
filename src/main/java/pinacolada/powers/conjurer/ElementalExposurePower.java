package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.utilities.ColoredString;
import pinacolada.actions.PCLActions;
import pinacolada.effects.PCLSFX;
import pinacolada.interfaces.listeners.OnElementalDebuffListener;
import pinacolada.interfaces.markers.MultiplicativePower;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class ElementalExposurePower extends PCLPower implements OnElementalDebuffListener, MultiplicativePower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, ElementalExposurePower.class);
    public static final int ELEMENTAL_MODIFIER = 25;
    public static final int DECAY_TURNS = 1;
    public int secondaryAmount;

    public ElementalExposurePower(AbstractCreature owner, int amount) {
        super(owner, POWER_ID);
        this.secondaryAmount = DECAY_TURNS;
        initialize(amount, PowerType.DEBUFF, true);
    }

    public static float calculatePercentage(AbstractCreature target) {
        return calculatePercentage(GameUtilities.getPowerAmount(target, POWER_ID));
    }

    public static float calculatePercentage(int amount) {
        return (100f + amount * ELEMENTAL_MODIFIER) / 100f;
    }

    @Override
    public float getPercentage(float initial, AbstractPCLElementalPower element, AbstractCreature owner) {
        return initial + calculatePercentage(amount);
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, amount * ELEMENTAL_MODIFIER, secondaryAmount);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.secondaryAmount = DECAY_TURNS;
    }

    @Override
    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
    }

    @Override
    protected ColoredString getSecondaryAmount(Color c) {
        return new ColoredString(secondaryAmount, Color.WHITE, c.a);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ATTACK_FIRE, 0.3f, 1.3f, 0.93f);
        PCLSFX.play(PCLSFX.ORB_FROST_CHANNEL, 0.3f, 1.3f, 0.93f);
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();

        if (this.secondaryAmount <= 0) {
            PCLActions.bottom.removePower(owner, owner, this);
        }
        else {
            this.secondaryAmount -= 1;
        }
    }
}
