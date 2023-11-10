package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

@VisibleCard
public class Cryostasis extends PCLCard {
    public static final PCLCardData DATA = register(Cryostasis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.SelfSingle)
            .setBlock(11, 3)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public Cryostasis() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(CMove.stabilize(PCLCardTarget.SelfSingle, IgnisPower.DATA, AquaPower.DATA));
    }
}
