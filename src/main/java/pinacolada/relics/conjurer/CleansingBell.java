package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class CleansingBell extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(CleansingBell.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.CLINK)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public CleansingBell() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.interactablePerCombat(2, PCond.exhaust(1, PCLCardGroupHelper.Hand), PMove.removeSelf(PCLPowerData.Weak, PCLPowerData.Vulnerable)));
    }
}