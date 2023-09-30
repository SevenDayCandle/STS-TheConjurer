package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class GlintstoneIcecrag extends PCLCard {
    public static final PCLCardData DATA = register(GlintstoneIcecrag.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(5, 2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public GlintstoneIcecrag() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.BLOW03);
        addUseMove(PMove.applyToSingle(3, AquaPower.DATA, CooledPower.DATA).setUpgrade(1));
    }
}
