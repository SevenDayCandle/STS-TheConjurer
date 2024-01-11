package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class GlintstoneArc extends PCLCard {
    public static final PCLCardData DATA = register(GlintstoneArc.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(3, 2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GlintstoneArc() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.SWORD01).setBonus(PMod.perPowerSelf(1, FlowPower.DATA).setExtra(14).setUpgradeExtra(6), 2);
    }
}
