package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Condensation extends PCLCard {
    public static final PCLCardData DATA = register(Condensation.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setAffinities(PCLAffinity.Blue.make(1, 1))
            .setCore();

    public Condensation() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.applyToSingle(1, PCLElementHelper.Gelus, PCLPowerHelper.Weak));
        addUseMove(PMove.triggerAlly(PCLCardTarget.Single, 2).setUpgrade(1));
    }
}
