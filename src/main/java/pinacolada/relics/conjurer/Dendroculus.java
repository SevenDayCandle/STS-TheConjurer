package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class Dendroculus extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(Dendroculus.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Dendroculus() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onTurnStart(), CMove.addLevel(2, PCLAffinity.Green)));
    }
}