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
public class Cornucopia extends PCLCard {
    public static final PCLCardData DATA = register(Cornucopia.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setTags(PCLCardTag.Purge)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.honkai);

    public Cornucopia() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.createDrawPile(3, FineFruit.DATA.ID));
        addUseMove(PMove.draw(1).setUpgrade(1));
    }
}
