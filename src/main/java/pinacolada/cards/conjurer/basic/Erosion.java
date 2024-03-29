package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.PetraPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Erosion extends PCLCard {
    public static final PCLCardData DATA = register(Erosion.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setBlock(8, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setCore();

    public Erosion() {
        super(DATA);
    }

    public void setup(Object input) {
        addBlockMove();
        addUseMove(PCond.exhaust(1, PCLCardGroupHelper.Hand), PMove.applyToEnemies(1, AquaPower.DATA, PetraPower.DATA).setUpgrade(1));
    }
}
