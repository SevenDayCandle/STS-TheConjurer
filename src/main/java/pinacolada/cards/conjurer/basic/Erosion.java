package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Erosion extends PCLCard {
    public static final PCLCardData DATA = register(Erosion.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setCore();

    public Erosion() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PCond.discardRandom(2, PCLCardGroupHelper.DrawPile), PMove.applyToEnemies(4, PCLElementHelper.Gelus, PCLElementHelper.Petra).setUpgrade(2));
        addUseMove(PCond.onDiscard(), CMove.gainReaction(6).setUpgrade(2));
    }
}
