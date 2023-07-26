package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class CrystalBarrage extends PCLCard {
    public static final PCLCardData DATA = register(CrystalBarrage.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(10, 0)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CrystalBarrage() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.EVFXForge01_12_FrostforgeShoot).setBonus(PMod.perPowerSingle(1, PCLElementHelper.Blasted), 1, 1);
    }
}
