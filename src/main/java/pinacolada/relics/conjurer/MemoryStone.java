package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.dungeon.CombatManager;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class MemoryStone extends PCLRelic {
    public static final PCLRelicData DATA = register(MemoryStone.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.CLINK);

    public MemoryStone() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            CombatManager.summons.addSummon(1);
        });
    }

    public int getValue() {
        return 1;
    }
}