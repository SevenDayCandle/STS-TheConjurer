package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Jyuratodus extends PCLCard {
    public static final PCLCardData DATA = register(Jyuratodus.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 2, 0)
            .setHp(11, 0)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Jyuratodus() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BITE);
        addUseMove(PMultiCond.or(PCond.onWithdraw(), PCond.onDeath()), CMove.stabilize(PCLCardTarget.Single, PetraPower.DATA));
    }
}
