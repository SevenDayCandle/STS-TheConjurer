package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class SunlightMedal extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SunlightMedal.class, ConjurerResources.conjurer)
            .setProps(RelicTier.RARE, LandingSound.CLINK);

    public SunlightMedal() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.combatEnd(PMod.perCard(1, PCLCardGroupHelper.MasterDeck).edit(f -> f.setType(PCLEnum.CardType.SUMMON)), PMove.gainGold(1)));
    }
}