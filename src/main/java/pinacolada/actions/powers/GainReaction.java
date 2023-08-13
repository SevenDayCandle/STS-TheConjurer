package pinacolada.actions.powers;

import com.megacrit.cardcrawl.core.Settings;
import pinacolada.actions.PCLAction;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.effects.PCLSFX;


public class GainReaction extends PCLAction<Integer> {
    protected boolean allowNegative = false;

    public GainReaction(int amount) {
        super(ActionType.SPECIAL, Settings.ACTION_DUR_XFAST);
        initialize(amount);
    }

    @Override
    protected void firstUpdate() {
        if (!allowNegative && amount < 0 && ConjurerReactionMeter.meter.getMatter() < -amount) {
            completeImpl();
            return;
        }

        ConjurerReactionMeter.meter.addCount(amount, true);
        PCLSFX.play(PCLSFX.POWER_FOCUS);

        complete(amount);
    }

    public GainReaction setAllowNegative(boolean val) {
        allowNegative = val;
        return this;
    }

}
