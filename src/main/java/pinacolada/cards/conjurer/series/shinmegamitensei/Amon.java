package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.delay.DelayTiming;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Amon extends PCLCard {
    public static final PCLCardData DATA = register(Amon.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.Single, DelayTiming.EndOfTurnFirst)
            .setDamage(4, 1)
            .setHp(9, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Amon() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLEnum.AttackEffect.CLAW);
        addGainPower(PTrigger.interactable(
                CCond.payMatter(9),
                PMod.bonusPerLevel(4, PCLAffinity.Red).setUpgrade(1), PMove.dealDamage(6, PCLEnum.AttackEffect.CLAW).setUpgrade(1)
        ));
    }
}
