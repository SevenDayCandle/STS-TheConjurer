package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class AerosolCoolant extends PCLCard {
    public static final PCLCardData DATA = register(AerosolCoolant.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.SelfSingle)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public AerosolCoolant() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.apply(PCLCardTarget.SelfSingle, 2, VentusPower.DATA).setUpgrade(1));
        addUseMove(PMove.withdrawAlly(PCLCardTarget.AllAlly));
    }
}
