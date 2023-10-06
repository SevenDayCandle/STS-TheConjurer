package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class PeriodicTable extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(PeriodicTable.class, ConjurerResources.conjurer)
            .setProps(RelicTier.STARTER, LandingSound.SOLID);

    public PeriodicTable() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.whenPerCombat(1, PCond.onSummon(), PMultiSkill.join(PMove.gainEnergy(1), PMove.gainBlock(PCLCardTarget.Team,1))));
    }
}