package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Repair extends PCLCard {
    public static final PCLCardData DATA = register(Repair.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public Repair() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gainBlock(PCLCardTarget.Team, 6).setUpgrade(3));
        addUseMove(PMove.remove(PCLCardTarget.Team, PCLPowerData.Frail, PCLPowerData.Vulnerable, PCLPowerData.Weak).setVFX(ConjurerEFK.MGC_HealingSpell_LV2));
    }
}
