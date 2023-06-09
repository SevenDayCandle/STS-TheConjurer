package pinacolada.actions.powers;

import com.megacrit.cardcrawl.core.Settings;
import pinacolada.actions.PCLAction;
import pinacolada.effects.PCLSFX;
import pinacolada.ui.combat.ConjurerReactionMeter;


public class GainReaction extends PCLAction<Integer> {

    public GainReaction(int amount) {
        super(ActionType.SPECIAL, Settings.ACTION_DUR_XFAST);
        initialize(amount);
    }

    @Override
    protected void firstUpdate() {
        ConjurerReactionMeter.meter.addCount(amount, true);
        PCLSFX.play(PCLSFX.POWER_FOCUS);

        complete(amount);
    }

}
