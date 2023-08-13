package pinacolada.cards.conjurer.series.genshinimpact;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class IttoArataki extends PCLCard {
    public static final PCLCardData DATA = register(IttoArataki.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(8, 1)
            .setHp(12, 2)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public IttoArataki() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addUseMove(PCond.isAttacking(PCLCardTarget.RandomEnemy), PMove.gainPlayer(5, PCLPowerHelper.NextTurnBlock));
    }
}
