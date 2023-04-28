package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Crystallize extends PCLCard {
    public static final PCLCardData DATA = register(Crystallize.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Blue)
            .setCore(true);

    public Crystallize() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.retain(2).setUpgrade(1), PMove.modifyBlock(2).useParent(true));
    }
}
