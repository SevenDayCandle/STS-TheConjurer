package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleRelic
public class PeriodicTable extends PCLRelic
{
    public static final String ID = createFullID(ConjurerResources.conjurer, PeriodicTable.class);

    public PeriodicTable()
    {
        super(ID, RelicTier.STARTER, LandingSound.SOLID);
    }

    @Override
    public void atBattleStart()
    {
        PCLActions.bottom.callback(() -> ConjurerReactionMeter.meter.addCount(getValue(), true));
    }

    public int getValue() {
        return 10;
    }
}