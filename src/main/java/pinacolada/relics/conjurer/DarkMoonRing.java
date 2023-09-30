package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class DarkMoonRing extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(DarkMoonRing.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public DarkMoonRing() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, CCond.checkLevel(1, PCLAffinity.Blue), PMove.applyToEnemies(3, PCLPowerData.Blinded)));
    }
}