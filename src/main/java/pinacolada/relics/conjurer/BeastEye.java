package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_OnAllyDeath;

@VisibleRelic
public class BeastEye extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(BeastEye.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.SOLID);

    public BeastEye() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, new PCond_OnAllyDeath(), PMove.applyToTeam(1, PCLPowerHelper.Strength)));
    }
}