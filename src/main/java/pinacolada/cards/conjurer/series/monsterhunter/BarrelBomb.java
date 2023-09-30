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

@VisibleCard
public class BarrelBomb extends PCLCard {
    public static final PCLCardData DATA = register(BarrelBomb.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(4, 0)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public BarrelBomb() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_EXPLOSION);
        addUseMove(PCond.checkAlly(PCLCardTarget.Single), PMove.dealDamageToAll(11, PCLAttackVFX.SMALL_EXPLOSION.key).setUpgrade(3));
    }
}
