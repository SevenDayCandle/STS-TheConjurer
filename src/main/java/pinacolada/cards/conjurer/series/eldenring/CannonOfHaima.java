package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PTrait;

@VisibleCard
public class CannonOfHaima extends PCLCard {
    public static final PCLCardData DATA = register(CannonOfHaima.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(9, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public CannonOfHaima() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.BLOW15).setChain(PMod.perCard(1, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Blue)), PTrait.damage(2).setUpgrade(1));
    }
}
