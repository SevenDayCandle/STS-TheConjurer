package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class AcidSurge extends PCLCard {
    public static final PCLCardData DATA = register(AcidSurge.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Single)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public AcidSurge() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToSingle(1, PCLPowerData.Weak, PCLPowerData.Vulnerable).setUpgrade(1));
        addUseMove(PCond.checkPowerSelf(5, FlowPower.DATA), PMove.applyToSingle(3, PCLPowerData.Poison).setUpgrade(1));
    }
}
