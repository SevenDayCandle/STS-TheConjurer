package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.ConjurerEFK;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class CaressingTears extends PCLCard {
    public static final PCLCardData DATA = register(CaressingTears.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Team)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public CaressingTears() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.gainTempHP(PCLCardTarget.Team, 6).setUpgrade(2));
        addUseMove(PMove.remove(PCLCardTarget.Team, PCLPowerHelper.Frail, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak).setVFX(ConjurerEFK.MGC_HealingSpell_LV2));
    }
}
