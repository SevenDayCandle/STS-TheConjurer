package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PTrait;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;
import pinacolada.skills.skills.base.modifiers.PMod_PerDistinctPower;

public class ColossalFormation extends PCLCard
{
    public static final PCLCardData DATA = register(ColossalFormation.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.UNCOMMON)
            .setBlock(13, 1)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public ColossalFormation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_PerReaction(4).setUpgrade(-1), PTrait.hasBlock(1));
    }
}
