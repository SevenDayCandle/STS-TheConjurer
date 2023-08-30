package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class NymphsConstancy extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(NymphsConstancy.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.CLINK)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public NymphsConstancy() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(2, PCond.onDiscard(), PMove.applyToEnemies(1, PCLElementHelper.Aqua)));
    }
}