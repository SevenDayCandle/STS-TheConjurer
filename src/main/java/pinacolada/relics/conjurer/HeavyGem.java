package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.annotations.VisibleRelic;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class HeavyGem extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(HeavyGem.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.SOLID)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public HeavyGem() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.whenPerCombat(1, PCond.onDraw(AbstractCard.CardType.ATTACK), PMove.modifyDamage(4).useParent(true)));
    }
}