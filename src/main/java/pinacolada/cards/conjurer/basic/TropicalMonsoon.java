package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class TropicalMonsoon extends PCLCard {
    public static final PCLCardData DATA = register(TropicalMonsoon.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(4, 2, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public TropicalMonsoon() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_Nature03);
        addUseMove(PMove.applyToEnemies(2, AquaPower.DATA, VentusPower.DATA));
    }
}
