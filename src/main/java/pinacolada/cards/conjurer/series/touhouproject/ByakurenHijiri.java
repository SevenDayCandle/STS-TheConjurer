package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

public class ByakurenHijiri extends PCLCard
{
    public static final PCLCardData DATA = register(ByakurenHijiri.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Single)
            .setBlock(2, 0)
            .setTags(PCLCardTag.Exhaust)
            .setRTags(PCLCardTag.Ethereal)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public ByakurenHijiri()
    {
        super(DATA);
    }


    public void setup(Object input)
    {
        addUseMove(new PMove_StabilizePower(PCLCardTarget.AllEnemy, 1, PCLPowerHelper.Burning, PCLPowerHelper.Flowed));
        addUseMove(PCond.semiLimited(), PCond.onExhaust(), PMove.gainTemporary(3, PCLPowerHelper.Resistance));
    }
}
