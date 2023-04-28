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
            .setAffinities(2, PCLAffinity.Green)
            .setCore();

    public FoliarTorque() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.cycle(1), PMove.applyToSingle(3, PCLElementHelper.Aer).setUpgrade(1));
    }
}
