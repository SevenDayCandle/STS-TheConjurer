package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MalleablePower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleRelic
public class Everamber extends PCLRelic {
    public static final PCLRelicData DATA = register(Everamber.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.FLAT);

    public Everamber() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addAdditionalPower(MalleablePower.POWER_ID);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addReaction(ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red));
            PCLActions.bottom.applyPower(AbstractDungeon.player, PCLPowerHelper.Malleable, getValue());
        });
    }

    public int getValue() {
        return 1;
    }
}