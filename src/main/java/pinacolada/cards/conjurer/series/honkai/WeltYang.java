package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class WeltYang extends PCLCard {
    public static final PCLCardData DATA = register(WeltYang.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Immaterial, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(2, 1)
            .setHp(7, 1)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.honkai);

    public WeltYang() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SMALL_LASER);
        addUseMove(CCond.linkFront(), PCond.checkPower(PCLCardTarget.Single, 2, PCLPowerData.Blinded).edit(f -> f.setNot(true)), PMove.applyToSingle(1, PCLPowerData.Blinded));
    }
}
