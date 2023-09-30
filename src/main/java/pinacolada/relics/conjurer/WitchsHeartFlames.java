package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.annotations.VisibleRelic;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.relics.PCLPointerRelic;
import pinacolada.relics.PCLRelicData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PTrigger;

@VisibleRelic
public class WitchsHeartFlames extends PCLPointerRelic {
    public static final PCLRelicData DATA = register(WitchsHeartFlames.class, ConjurerResources.conjurer)
            .setProps(RelicTier.UNCOMMON, LandingSound.MAGICAL)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public WitchsHeartFlames() {
        super(DATA);
    }

    public void setup() {
        addUseMove(PTrigger.when(PCond.onDraw(AbstractCard.CardType.STATUS),
                PMultiSkill.join(PMove.applyToEnemies(2, IgnisPower.DATA, BlastedPower.DATA))));
    }
}