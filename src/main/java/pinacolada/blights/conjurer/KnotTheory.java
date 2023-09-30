package pinacolada.blights.conjurer;

import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleBlight;
import pinacolada.blights.PCLBlight;
import pinacolada.blights.PCLBlightData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.ConjurerElementButton;
import pinacolada.dungeon.ConjurerReactionMeter;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleBlight
public class KnotTheory extends PCLBlight {
    public static final PCLBlightData DATA = register(KnotTheory.class, ConjurerResources.conjurer);

    public KnotTheory() {
        super(DATA);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();

        PCLActions.top.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Blue).addReaction(PCLAffinity.Red);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red).addReaction(PCLAffinity.Orange);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Orange).addReaction(PCLAffinity.Green);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addReaction(PCLAffinity.Blue);
            for (ConjurerElementButton element : ConjurerReactionMeter.meter.getElementButtons()) {
                element.currentAmplifyOffset = AbstractPCLElementalPower.BASE_DAMAGE_MULTIPLIER * -getValue() / 100;
                CombatManager.addEffectBonus(element.power.ID, -getValue());
            }
        });
    }

    public float getMultiplier() {
        return (100 + getValue()) / 100f;
    }

    public String getUpdatedDescription() {
        return formatDescription(0, getValue());
    }

    public int getValue() {
        return 50;
    }
}