package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.ElementPowerData;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class PoisonArmament extends PCLCard {
    public static final PCLCardData DATA = register(PoisonArmament.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setAffinities(1, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public PoisonArmament() {
        super(DATA);
    }

    public void setup(Object input) {
        addGainPower(2, PTrigger.when(PCond.damage(PCLCardTarget.Self, 1), PMove.applyToSingle(1, VentusPower.DATA, ElementPowerData.Poison)));
    }
}
