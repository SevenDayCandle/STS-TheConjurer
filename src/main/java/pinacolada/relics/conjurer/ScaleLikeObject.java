package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class ScaleLikeObject extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ScaleLikeObject.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public ScaleLikeObject() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onSummon(), PMove.apply(PCLCardTarget.UseParent,2, PCLPowerData.PlatedArmor)));
    }
}