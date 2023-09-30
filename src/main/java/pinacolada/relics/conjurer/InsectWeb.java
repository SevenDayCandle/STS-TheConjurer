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
public class InsectWeb extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(InsectWeb.class, ConjurerResources.conjurer)
            .setProps(RelicTier.SHOP, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.honkai);

    public InsectWeb() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onTurnStart(), PMove.apply(PCLCardTarget.RandomAlly, 2, PCLPowerData.Envenom, PCLPowerData.Poison)));
    }
}