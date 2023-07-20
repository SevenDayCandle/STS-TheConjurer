package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class ConcertsFinalHour extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ConcertsFinalHour.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.CLINK);

    public ConcertsFinalHour() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onTurnEnd(),
                PCond.pileHas(5, PCLCardGroupHelper.DrawPile).edit(f -> f.setNot(true)),
                PMod.perCard(PCLCardGroupHelper.DiscardPile),
                CMove.gainMatter(2)
        ));
    }
}