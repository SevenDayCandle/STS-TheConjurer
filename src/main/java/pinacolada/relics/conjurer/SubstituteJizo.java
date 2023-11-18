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
public class SubstituteJizo extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SubstituteJizo.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SubstituteJizo() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.whenPerCombat(1, PCond.haveLostHP(), PMove.playCopy(1, PCLCardTarget.RandomAlly).useParent(true)));
    }
}