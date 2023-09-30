package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.annotations.VisibleRelic;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class SunlightMedal extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(SunlightMedal.class, ConjurerResources.conjurer)
            .setProps(RelicTier.BOSS, LandingSound.CLINK)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SunlightMedal() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.combatEnd(PMod.perCard(1, PCLCardGroupHelper.MasterDeck).edit(f -> f.setType(AbstractCard.CardType.POWER)), PMove.gainGold(2)));
    }
}