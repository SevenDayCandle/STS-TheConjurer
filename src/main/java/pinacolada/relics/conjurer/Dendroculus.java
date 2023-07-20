package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class Dendroculus extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(Dendroculus.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.MAGICAL);

    public Dendroculus() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onTurnStart(), PMod.bonusPerLevel(2, PCLAffinity.Green), CMove.gainMatter(5)));
    }
}