package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Desiccation extends PCLCard {
    public static final PCLCardData DATA = register(Desiccation.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON, PCLCardTarget.Any)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public Desiccation() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.retain(2).setUpgrade(1));
        addUseMove(PMove.apply(PCLCardTarget.Any, 1, VentusPower.DATA, PetraPower.DATA));
    }
}
