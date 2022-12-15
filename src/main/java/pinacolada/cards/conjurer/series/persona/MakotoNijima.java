package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.modifiers.PMod_ReshufflePerCard;

public class MakotoNijima extends PCLCard
{
    public static final PCLCardData DATA = register(MakotoNijima.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setBlock(13, 3)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.persona);

    public MakotoNijima()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.cooldown(2), PMove.selfTransform(MakotoNijima_Johanna.DATA));
        addUseMove(new PMod_ReshufflePerCard(2, PCLCardGroupHelper.Hand).setAffinity(PCLAffinity.Orange), PMove.gainTemporary(3, PCLPowerHelper.Thorns));
    }
}
