package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;

public class RinnosukeMorichika extends PCLCard
{
    public static final PCLCardData DATA = register(RinnosukeMorichika.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public RinnosukeMorichika()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onTurnStart(), new PMove_GainReaction(4).setUpgrade(2)));
        addGainPower(PTrigger.interactable(new PCond_PayReaction(6), PMove.scry(2)));
    }
}
