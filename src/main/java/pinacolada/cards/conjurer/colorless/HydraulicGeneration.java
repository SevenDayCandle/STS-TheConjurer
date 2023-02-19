package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class HydraulicGeneration extends PCLCard
{
    public static final PCLCardData DATA = register(HydraulicGeneration.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore(true);

    public HydraulicGeneration()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.draw(1).setUpgrade(1));
        addGainPower(2, PTrigger.when(PCond.onTurnStart(), PMove.draw(1).edit(f -> f.setType(CardType.ATTACK, CardType.SKILL))));
    }
}
