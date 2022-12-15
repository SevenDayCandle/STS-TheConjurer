package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

public class Airavata extends PCLCard
{
    public static final PCLCardData DATA = register(Airavata.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.UNCOMMON)
            .setBlock(12, 1)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Airavata()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_PerReaction(4).setUpgrade(-1), PTrait.hasBlock(1));
        addUseMove(PCond.checkLevel(2, PCLAffinity.Blue), PMove.retain(2));
    }
}
