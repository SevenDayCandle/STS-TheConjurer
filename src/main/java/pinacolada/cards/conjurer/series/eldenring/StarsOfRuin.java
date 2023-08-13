package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;

@VisibleCard
public class StarsOfRuin extends PCLCard {
    public static final PCLCardData DATA = register(StarsOfRuin.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Immaterial, PCLCardTarget.RandomEnemy)
            .setDamage(2, 1, 8)
            .setAffinities(1, PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public StarsOfRuin() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.BLOW08).setChain(PMod.perLevel(1, PCLAffinity.Blue), PTrait.hitCount(2));
        addUseMove(PCond.checkLevel(3, PCLAffinity.Blue), PMove.gainTempHP(6));
    }
}
