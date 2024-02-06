package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class ShardSpiral extends PCLCard {
    public static final PCLCardData DATA = register(ShardSpiral.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(7, 3)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ShardSpiral() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.BLOW03);
        addUseMove(PMove.createDiscardPile(2, SwiftGlintstoneShard.DATA.ID));
    }
}
