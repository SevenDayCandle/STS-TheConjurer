package pinacolada.relics.conjurer;

import extendedui.utilities.CostFilter;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class CarpenterBug extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(CarpenterBug.class, ConjurerResources.conjurer)
            .setProps(RelicTier.COMMON, LandingSound.FLAT)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public CarpenterBug() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(1, PCond.onOtherCardPlayed(PCLEnum.CardType.SUMMON).edit(f -> f.setCost(CostFilter.Cost2, CostFilter.Cost3, CostFilter.Cost4, CostFilter.Cost5)), PMove.gainBlock(PCLCardTarget.Self, 4)));
    }
}