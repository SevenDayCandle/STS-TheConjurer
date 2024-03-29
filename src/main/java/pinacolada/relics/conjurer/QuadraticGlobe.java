package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.MalleablePower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.special.primary.PRoot;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class QuadraticGlobe extends PCLRelic {
    public static final PCLRelicData DATA = register(QuadraticGlobe.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.CLINK);

    public QuadraticGlobe() {
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