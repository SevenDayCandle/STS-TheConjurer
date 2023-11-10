package pinacolada.cards.conjurer.series.honkai;


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
import pinacolada.skills.PMove;

@VisibleCard
public class DartingArrow extends PCLCard {
    public static final PCLCardData DATA = register(DartingArrow.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(8, 3)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.honkai);

    public DartingArrow() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND).setBonus(PCond.checkCreature(PCLCardTarget.AllAlly, 1), 4, 1);
        addUseMove(PMove.withdrawAlly(PCLCardTarget.RandomAlly));
    }
}
