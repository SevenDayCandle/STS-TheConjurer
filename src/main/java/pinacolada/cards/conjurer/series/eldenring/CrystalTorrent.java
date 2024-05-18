package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class CrystalTorrent extends PCLCard {
    public static final PCLCardData DATA = register(CrystalTorrent.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(10, 3)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CrystalTorrent() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.EVFXForge01_12_FrostforgeShoot).setBonus(PMod.perPowerSingle(2, BlastedPower.DATA), 1);
    }
}
