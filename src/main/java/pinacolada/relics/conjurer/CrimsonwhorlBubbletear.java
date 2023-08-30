package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class CrimsonwhorlBubbletear extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(CrimsonwhorlBubbletear.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.CLINK)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CrimsonwhorlBubbletear() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.whenPerCombat(1, PCond.haveTakenDamage(), PCond.hpPercent(PCLCardTarget.Self, 20).edit(f -> f.setNot(true)), PMove.healPercent(PCLCardTarget.Self, 20)));
    }
}