package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
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
            .setDamage(10, 1, 0)
            .setHp(16, 0)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Nergigante() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PCond.cooldown(1), PMove.dealDamage(3, PCLEnum.AttackEffect.EARTH, PCLCardTarget.AllAlly), PMove.gain(2, PCLPowerHelper.Strength));
    }
}
