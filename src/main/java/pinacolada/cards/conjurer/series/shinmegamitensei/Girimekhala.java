package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Girimekhala extends PCLCard {
    public static final PCLCardData DATA = register(Girimekhala.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(6, 0)
            .setHp(16, 3)
            .setAffinities(PCLAffinity.Orange.make(), PCLAffinity.Purple.make())
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Girimekhala() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addGainPower(PTrigger.interactable(
                PCond.exhaustRandom(3, PCLCardGroupHelper.DrawPile),
                PMod.increaseOnUse(9), PMove.gainBlock(9)
        ));
    }
}
