package pinacolada.cards.conjurer.series.honkai;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;

@VisibleCard
public class JingYuan extends PCLCard {
    public static final PCLCardData DATA = register(JingYuan.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(6, 0)
            .setHp(15, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.honkai);

    public JingYuan() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addUseMove(PCond.cooldown(3), PMove.dealDamageToAll(19, AbstractGameAction.AttackEffect.LIGHTNING).setUpgrade(5));
    }
}
