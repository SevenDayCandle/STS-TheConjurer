package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.moves.PMove_ReshuffleToTop;

public class AliceMargatroid extends PCLCard
{
    public static final PCLCardData DATA = register(AliceMargatroid.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setMaxCopies(3)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public AliceMargatroid()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onTurnStart(), PMultiSkill.join(PMove.draw(1).setUpgrade(1), new PMove_ReshuffleToTop(1, PCLCardGroupHelper.Hand))));
        addGainPower(PTrigger.interactable(new PCond_PayReaction(8), PMove.gain(1, PCLPowerHelper.NextTurnDraw)));
    }

}
