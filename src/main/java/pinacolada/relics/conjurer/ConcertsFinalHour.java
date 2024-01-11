package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.powers.PCLPowerData;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class ConcertsFinalHour extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(ConcertsFinalHour.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.CLINK)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public ConcertsFinalHour() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onTurnEnd(),
                PCond.pileHas(5, PCLCardGroupHelper.DrawPile).edit(f -> f.setNot(true)),
                PMod.perCard(4, PCLCardGroupHelper.DiscardPile),
                PMove.gain(1, PCLPowerData.Energized)
        ));
    }
}