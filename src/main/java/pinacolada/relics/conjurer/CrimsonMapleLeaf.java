package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MalleablePower;
import extendedui.interfaces.delegates.ActionT0;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.relics.PCLRelic;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.ConjurerReactionMeter;

@VisibleRelic
public class CrimsonMapleLeaf extends PCLRelic
{
    public static final String ID = createFullID(ConjurerResources.conjurer, CrimsonMapleLeaf.class);

    public CrimsonMapleLeaf()
    {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL, ConjurerResources.conjurer.playerClass);
    }

    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth(getValue());
    }

    @Override
    protected void activateBattleEffect()
    {
        PCLActions.bottom.callback((ActionT0) () -> {
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addAdditionalPower(MalleablePower.POWER_ID);
            ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Green).addCombustion(ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Red));
            ConjurerReactionMeter.meter.getReactionButton(PCLAffinity.Red, PCLAffinity.Orange).switchType();
        });
    }

    public int getValue()
    {
        return 7;
    }
}