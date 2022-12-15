package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.*;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_ExhaustBranch;

public class SuikaIbuki extends PCLCard
{
    public static final PCLCardData DATA = register(SuikaIbuki.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setBlock(5, array(3, 1), 2, array(0, 0))
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public SuikaIbuki()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_ExhaustBranch(1, PCLCardGroupHelper.DiscardPile).setUpgrade(0, 1).setAffinity(PCLAffinity.Blue), PMultiSkill.join(
                PMove.draw(1),
                new PMove_GainReaction(4).setUpgrade(1)
        ));
    }
}
