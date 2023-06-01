package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class HydraulicGeneration extends PCLCard {
    public static final PCLCardData DATA = register(HydraulicGeneration.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.None)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Silver)
            .setCore(true);

    public HydraulicGeneration() {
        super(DATA);
    }

    public void setup(Object input) {
        addUseMove(PMove.draw(2).setUpgrade(1));
        addGainPower(2, PTrigger.when(PCond.onTurnStart(), PMove.fetchRandom(1, PCLCardGroupHelper.DrawPile).edit(f -> f.setType(CardType.ATTACK, CardType.SKILL))));
    }
}
