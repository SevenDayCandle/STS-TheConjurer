package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class PowerItem extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(PowerItem.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public PowerItem() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.interactablePerCombat(3, PMove.loseHp(3), PMove.gain(2, PCLPowerData.Strength)));
    }
}