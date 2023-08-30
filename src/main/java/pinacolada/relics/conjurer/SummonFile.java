package pinacolada.relics.conjurer;

import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.primary.PRoot;

@VisibleRelic
public class SummonFile extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SummonFile.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public SummonFile() {
        super(DATA);
    }

    public void setup() {
        addUseMove(new PRoot(), PMove.createRandom(1, 3, PCLCardGroupHelper.Hand).edit(f -> f.setType(PCLEnum.CardType.SUMMON)), PMove.modifyCostExactForTurn(0).useParent(true));
    }
}