package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.basic.Incineration;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Baphomet extends PCLCard {
    public static final PCLCardData DATA = register(Baphomet.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(5, 0)
            .setHp(13, 4)
            .setAffinities(PCLAffinity.Red.make(), PCLAffinity.Purple.make(2))
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Baphomet() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addGainPower(PTrigger.interactable(
                PCond.takeDamageTo(6).setUpgrade(-1),
                PMove.create(2, Incineration.DATA.ID), PMove.modifyCostExact(0, 1).useParent(true))
        );
    }
}
