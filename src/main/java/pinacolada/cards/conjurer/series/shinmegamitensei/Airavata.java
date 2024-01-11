package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Airavata extends PCLCard {
    public static final PCLCardData DATA = register(Airavata.class, ConjurerResources.conjurer)
            .setSummon(3, CardRarity.COMMON, PCLAttackType.Normal, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(3, 0)
            .setHp(16, 3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Airavata() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addGainPower(PTrigger.interactable(
                CCond.payLevel(3, PCLAffinity.Orange).setUpgrade(-1),
                PMove.retain(2)
        ));
    }
}
