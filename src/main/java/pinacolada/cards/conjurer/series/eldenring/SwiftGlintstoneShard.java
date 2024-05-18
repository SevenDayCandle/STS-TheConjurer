package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class SwiftGlintstoneShard extends PCLCard {
    public static final PCLCardData DATA = register(SwiftGlintstoneShard.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.SPECIAL, PCLAttackType.Ranged)
            .setDamage(5, 5)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public SwiftGlintstoneShard() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.BLOW04);
        addUseMove(PMultiCond.or(PCond.onDraw(), PCond.onDiscard()), PMove.gain(1, FlowPower.DATA));
    }
}
