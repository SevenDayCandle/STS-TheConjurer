package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Thermogenesis extends PCLCard {
    public static final PCLCardData DATA = register(Thermogenesis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(3, 1, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setCore();

    public Thermogenesis() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PMultiCond.or(PCond.onDraw(), PCond.onDiscard()), PMove.applyToRandom(3, IgnisPower.DATA).setUpgrade(1));
    }
}
