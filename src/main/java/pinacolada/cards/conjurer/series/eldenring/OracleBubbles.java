package pinacolada.cards.conjurer.series.eldenring;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class OracleBubbles extends PCLCard {
    public static final PCLCardData DATA = register(OracleBubbles.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public OracleBubbles() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMultiSkill.join(PMove.applyToSingle(2, AquaPower.DATA).setVFX(ConjurerEFK.MGC_HealingSpell_LV1).setUpgrade(2), PMove.applyToSingle(1, PCLPowerData.Weak)));
    }
}
