package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class PortableCactus extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(PortableCactus.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.FLAT);

    public PortableCactus() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.haveTakenDamage(), PMultiSkill.join(PMove.gainBlockPlayer(1), PMove.dealDamageToRandom(1))));
    }
}