package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class FoliarTorque extends PCLCard {
    public static final PCLCardData DATA = register(FoliarTorque.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON, PCLCardTarget.Single)
            .setBlock(3, 2)
            .setAffinities(PCLAffinity.Green.make(1, 1))
            .setCore();

    public FoliarTorque() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PCond.cycle(1), PMove.applyToSingle(4, PCLElementHelper.Ventus));
    }
}
