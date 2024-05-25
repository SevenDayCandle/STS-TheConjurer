package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PDelay;
import pinacolada.skills.PMove;

@VisibleCard
public class ProjectedHeal extends PCLCard {
    public static final PCLCardData DATA = register(ProjectedHeal.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls)
            .setTags(PCLCardTag.Purge);

    public ProjectedHeal() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PDelay.turnStart(2), PMove.heal(10).setUpgrade(3));
        addUseMove(PCond.onExhaust(), PMove.gainTempHP(7));
    }
}
