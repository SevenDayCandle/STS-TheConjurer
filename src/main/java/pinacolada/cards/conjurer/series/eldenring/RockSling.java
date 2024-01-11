package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;

@VisibleCard
public class RockSling extends PCLCard {
    public static final PCLCardData DATA = register(RockSling.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(8, 2, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public RockSling() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH).setChain(PCond.checkPower(PCLCardTarget.Any, 1, PetraPower.DATA), PTrait.hitCount(1));
    }
}
