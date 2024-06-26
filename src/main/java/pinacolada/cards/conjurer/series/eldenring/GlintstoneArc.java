package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class GlintstoneArc extends PCLCard {
    public static final PCLCardData DATA = register(GlintstoneArc.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(6, 3)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GlintstoneArc() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.SWORD01).setBonus(PMod.perPowerSelf(2, FlowPower.DATA), 1);
    }
}
