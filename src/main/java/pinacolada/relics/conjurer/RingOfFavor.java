package pinacolada.relics.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PTrait;

@VisibleRelic
public class RingOfFavor extends PCLRelic {
    public static final PCLRelicData DATA = register(RingOfFavor.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.CLINK)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public RingOfFavor() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.addReaction(PCLAffinity.Red, PCLAffinity.Blue, PTrait.damageMultiplier(getValue()));
            ConjurerReactionMeter.meter.addReaction(PCLAffinity.Green, PCLAffinity.Orange, PTrait.blockMultiplier(getValue()));
        });
    }

    @Override
    public int getValue() {
        return 50;
    }
}