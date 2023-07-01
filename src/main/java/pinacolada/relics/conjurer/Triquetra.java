package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.dungeon.CombatManager;
import pinacolada.interfaces.subscribers.OnTryApplyPowerSubscriber;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.relics.PCLRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerElementButton;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleRelic
public class Triquetra extends PCLRelic implements OnTryApplyPowerSubscriber {
    public static final PCLRelicData DATA = register(Triquetra.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.MAGICAL);

    public Triquetra() {
        super(DATA);
    }

    @Override
    protected void activateBattleEffect() {
        PCLActions.bottom.callback(() -> {
            // Simplify element reactions
            for (ConjurerElementButton b : ConjurerReactionMeter.meter.getElementButtons()) {
                b.initialize();
            }
            ConjurerReactionMeter.meter.disableAffinity(PCLAffinity.Orange);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addReaction(ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red));
        });
    }

    public int getValue() {
        return 2;
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        CombatManager.subscribe(this);
    }

    @Override
    public boolean tryApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1, AbstractGameAction abstractGameAction) {
        if (abstractPower instanceof PetraPower) {
            PCLActions.bottom.applyPower(PCLCardTarget.None, PCLPowerHelper.NextTurnBlock, abstractPower.amount);
            return false;
        }
        return true;
    }
}