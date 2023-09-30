package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Asta extends PCLCard {
    public static final PCLCardData DATA = register(Asta.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(3, 2)
            .setHp(4, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.honkai);

    public Asta() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.FIRE);
        addUseMove(CCond.link(PCLAffinity.Blue, PCLAffinity.Orange), PMove.applyToSingle(1, IgnisPower.DATA));
    }
}
