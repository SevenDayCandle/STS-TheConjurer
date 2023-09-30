package pinacolada.actions.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLAction;
import pinacolada.interfaces.markers.StablizingPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.TemporaryPower;
import pinacolada.powers.conjurer.StabilizingPower;
import pinacolada.utilities.GameUtilities;

public class StabilizePowerAction extends PCLAction<AbstractPower> {
    public String powerID;
    public boolean showEffect = true;
    public boolean isFast = true;

    public StabilizePowerAction(AbstractCreature source, AbstractCreature target, PCLPowerData ph, int amount) {
        this(source, target, ph.ID, amount);
    }

    public StabilizePowerAction(AbstractCreature source, AbstractCreature target, String powerID, int amount) {
        super(ActionType.POWER, Settings.ACTION_DUR_XFAST);
        initialize(source, target, amount);
        this.powerID = powerID;
    }

    @Override
    protected void firstUpdate() {
        final AbstractPower sourcePower = GameUtilities.getPower(target, powerID);

        if (sourcePower instanceof StablizingPower) {
            ((StablizingPower) sourcePower).stabilize(amount);
        }
        else if (sourcePower instanceof TemporaryPower) {
            ((TemporaryPower) sourcePower).stabilize(amount);
        }
        else if (sourcePower != null && GameUtilities.isTurnBasedPower(sourcePower)) {
            GameUtilities.applyPowerInstantly(target, new StabilizingPower(sourcePower, target, source, amount));
        }

        complete(sourcePower);
    }

    public StabilizePowerAction showEffect(boolean showEffect, boolean isFast) {
        this.showEffect = showEffect;
        this.isFast = isFast;

        return this;
    }

}
