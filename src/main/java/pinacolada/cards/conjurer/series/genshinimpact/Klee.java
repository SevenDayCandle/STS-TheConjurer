package pinacolada.cards.conjurer.series.genshinimpact;


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
public class Klee extends PCLCard {
    public static final PCLCardData DATA = register(Klee.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Immaterial, PCLCardTarget.RandomEnemy)
            .setDamage(1, array(2, 0))
            .setHp(3, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Klee() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addUseMove(PCond.cooldown(2).setUpgrade(0, -1), PMove.createDrawPile(1, Klee_JumpyDumpty.DATA.ID));
    }
}
