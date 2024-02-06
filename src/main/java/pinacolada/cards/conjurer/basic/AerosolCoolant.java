package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.moves.PMove_StabilizePower;

@VisibleCard
public class AerosolCoolant extends PCLCard {
    public static final PCLCardData DATA = register(AerosolCoolant.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.SelfSingle)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public AerosolCoolant() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(new PMove_StabilizePower(PCLCardTarget.SelfSingle, AquaPower.DATA));
        addUseMove(PMove.withdrawAlly(PCLCardTarget.AllAlly));
    }
}
