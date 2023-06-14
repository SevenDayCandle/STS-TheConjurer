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
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Nargacuga extends PCLCard {
    public static final PCLCardData DATA = register(Nargacuga.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(2, 1, 2)
            .setHp(7, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.monsterHunter);

    public Nargacuga() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onWithdraw()), PMove.draw(2));
    }
}
