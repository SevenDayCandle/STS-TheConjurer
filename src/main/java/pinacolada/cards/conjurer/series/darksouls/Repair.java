package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Repair extends PCLCard {
    public static final PCLCardData DATA = register(Repair.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setCostUpgrades(-1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Repair() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.remove(PCLCardTarget.Team, PCLPowerData.Frail, PCLPowerData.Weak).setVFX(ConjurerEFK.MGC_HealingSpell_LV2));
        addUseMove(PMod.payPerPower(2, ForgingPower.DATA).setExtra(20), PMove.gain(1, PCLPowerData.PlatedArmor));
    }
}
