package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyDeath;

@VisibleRelic
public class HellBull extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(HellBull.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public HellBull() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(new PCond_OnAllyDeath(), PMove.gainTempHP(2)));
    }
}