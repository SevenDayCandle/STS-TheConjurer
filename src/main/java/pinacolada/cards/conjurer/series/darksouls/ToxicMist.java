package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.conditions.PCond_React;

@VisibleCard
public class ToxicMist extends PCLCard {
    public static final PCLCardData DATA = register(ToxicMist.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public ToxicMist() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToEnemies(8, PCLPowerData.Poison));
        addUseMove(new PCond_React(PCLAffinity.Blue), PMove.applyToEveryone(1, PCLPowerData.Vulnerable));
    }
}
