package pinacolada.cards.conjurer.series.monsterhunter;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class KushalaDaora extends PCLCard {
    public static final PCLCardData DATA = register(KushalaDaora.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(3, 1, 2)
            .setHp(13, 1)
            .setAffinities(2, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public KushalaDaora() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addGainPower(PTrigger.when(PCond.onWithdraw(), PMove.gainPlayer(3, FlowPower.DATA)));
    }
}
