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
public class Bailu extends PCLCard {
    public static final PCLCardData DATA = register(Bailu.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON, PCLAttackType.Immaterial)
            .setDamage(2, 1)
            .setHp(4, 2)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.honkai);

    public Bailu() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WATER);
        addUseMove(PCond.isAttacking(PCLCardTarget.Single).edit(f -> f.setNot(true)), PMove.gainTempHP(2));
    }
}
