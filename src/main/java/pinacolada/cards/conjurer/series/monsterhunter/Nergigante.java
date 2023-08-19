package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class Nergigante extends PCLCard {
    public static final PCLCardData DATA = register(Nergigante.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(10, array(2, 0), 1, array(0, 0))
            .setHp(25, array(0, 3))
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Nergigante() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.onDeath(), PMove.dealDamage(45, PCLEnum.AttackEffect.EARTH, PCLCardTarget.AllEnemy).setUpgrade(0, 12));
    }
}
