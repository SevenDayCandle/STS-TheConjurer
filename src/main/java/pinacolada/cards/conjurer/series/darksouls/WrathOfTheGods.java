package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class WrathOfTheGods extends PCLCard {
    public static final PCLCardData DATA = register(WrathOfTheGods.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Single)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public WrathOfTheGods() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.exhaust(1),
                PMove.play(3, PCLCardTarget.Single, PCLCardGroupHelper.DiscardPile).setUpgrade(1).edit(f -> f.setOrigin(PCLCardSelection.Random)));
    }
}
