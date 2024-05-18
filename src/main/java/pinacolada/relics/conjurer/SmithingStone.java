package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.interfaces.subscribers.OnSpecificPowerActivatedSubscriber;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class SmithingStone extends PCLRelic implements OnSpecificPowerActivatedSubscriber {
    public static final PCLRelicData DATA = register(SmithingStone.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.CLINK);

    public SmithingStone() {
        super(DATA);
    }

    @Override
    public boolean onPowerActivated(AbstractPower power, AbstractCreature abstractCreature, boolean originalValue) {
        if (power instanceof ForgingPower && ForgingPower.targetCard != null) {
            GameUtilities.modifyTag(ForgingPower.targetCard, PCLCardTag.Recast, getValue(), true);
        }
        return originalValue;
    }

    @Override
    public int getValue() {
        return 1;
    }
}