package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

@VisibleCard
public class Cryostasis extends PCLCard {
    public static final PCLCardData DATA = register(Cryostasis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.SelfSingle)
            .setBlock(6, 3)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public Cryostasis() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(new PMove_StabilizePower(PCLCardTarget.SelfSingle, BlastedPower.DATA, CooledPower.DATA));
    }
}
