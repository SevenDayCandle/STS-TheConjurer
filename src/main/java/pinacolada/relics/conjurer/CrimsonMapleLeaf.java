package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.powers.MalleablePower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class CrimsonMapleLeaf extends PCLRelic
{
    public static final String ID = createFullID(ConjurerResources.conjurer, CrimsonMapleLeaf.class);

    public CrimsonMapleLeaf()
    {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass);
    }

    @Override
    protected void activateBattleEffect()
    {
        PCLActions.bottom.callback(() -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addAdditionalPower(PoisonPower.POWER_ID);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addAdditionalPower(MalleablePower.POWER_ID);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addCombustion(ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red));
        });
    }
}