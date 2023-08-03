package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;

@VisibleCard
public class CrystalHail extends PCLCard {
    public static final PCLCardData DATA = register(CrystalHail.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(3, 1, 3)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CrystalHail() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_W2_Shield_Break).setChain(PMod.perLevel(1, PCLAffinity.Blue), PTrait.hitCount(1));
    }
}
