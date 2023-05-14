package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.powers.common.InnovationPower;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

public class SpiritPoop3 extends PCLRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SPECIAL, LandingSound.SOLID);

    public SpiritPoop3() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        PCLActions.bottom.applyPower(new InnovationPower(player, 2));
    }
}