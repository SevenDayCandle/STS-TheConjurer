package pinacolada.cards.conjurer.series.monsterhunter;


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
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Volvidon extends PCLCard {
    public static final PCLCardData DATA = register(Volvidon.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(4, array(2, -1), 1, array(0, 1))
            .setHp(6, 0)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Volvidon() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onDeath()), PMove.cycle(2));
    }
}
