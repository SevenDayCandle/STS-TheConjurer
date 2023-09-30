package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class FineFruit extends PCLCard {
    public static final PCLCardData DATA = register(FineFruit.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.Team)
            .setTags(PCLCardTag.Purge)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.honkai);

    public FineFruit() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.heal(PCLCardTarget.Team, 4).setUpgrade(3));
    }
}
