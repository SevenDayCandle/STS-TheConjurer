package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;

@VisibleCard
public class CannonOfHaima extends PCLCard {
    public static final PCLCardData DATA = register(CannonOfHaima.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(6, 0)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CannonOfHaima() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.ICE).setChain(PMod.perCard(1, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Blue)), PTrait.damage(4).setUpgrade(1));
    }
}
