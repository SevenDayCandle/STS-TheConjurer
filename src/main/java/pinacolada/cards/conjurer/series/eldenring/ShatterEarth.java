package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;

@VisibleCard
public class ShatterEarth extends PCLCard {
    public static final PCLCardData DATA = register(ShatterEarth.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(14, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ShatterEarth() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH).setChain(PCond.block(PCLCardTarget.Single, 1), PTrait.damage(10).setUpgrade(3));
    }
}
