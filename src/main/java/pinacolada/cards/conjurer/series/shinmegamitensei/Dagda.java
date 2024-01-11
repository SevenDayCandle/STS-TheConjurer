package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Dagda extends PCLCard {
    public static final PCLCardData DATA = register(Dagda.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy, DelayTiming.EndOfTurnFirst)
            .setDamage(3, 1)
            .setHp(11, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Dagda() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addGainPower(PTrigger.interactable(
                CCond.payLevel(3, PCLAffinity.Orange),
                PMove.gainTemporaryPlayer(1, PCLPowerData.Artifact))
        );
    }
}
