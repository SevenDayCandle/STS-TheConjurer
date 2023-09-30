package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.MalleablePower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

@VisibleRelic
public class Everamber extends PCLRelic {
    public static final PCLRelicData DATA = register(Everamber.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Everamber() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addAdditionalPower(MalleablePower.POWER_ID);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Orange).addAdditionalPower(MalleablePower.POWER_ID);
            for (AbstractCreature c : GameUtilities.getAllCharacters(true)) {
                PCLActions.bottom.applyPower(c, PCLPowerData.Malleable, getValue());
            }
        });
    }

    public int getValue() {
        return 2;
    }
}