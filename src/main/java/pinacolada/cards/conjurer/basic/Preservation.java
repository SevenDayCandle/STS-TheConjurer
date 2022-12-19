package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.base.modifiers.PMod_PerDistinctPower;

public class Preservation extends PCLCard
{
    public static final PCLCardData DATA = register(Preservation.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Team)
            .setBlock(7, 1)
            .setAffinities(PCLAffinity.Orange)
            .setCore();

    public Preservation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.retain(1).setUpgrade(1));
    }
}
