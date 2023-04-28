package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Deluge extends PCLCard {
    public static final PCLCardData DATA = register(Deluge.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Team)
            .setBlock(3, 1)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public Deluge() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMove.modifyAffinity(1, 2, PCLAffinity.Blue).setUpgradeExtra(1).edit(f -> f.setCardGroup(PCLCardGroupHelper.Hand)));
    }
}
