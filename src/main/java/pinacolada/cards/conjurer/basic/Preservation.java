package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

public class Preservation extends PCLCard
{
    public static final PCLCardData DATA = register(Preservation.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Orange)
            .setCore();

    public Preservation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.retain(2).setUpgrade(1));
        addUseMove(PMod.bonusPerLevel(3, PCLAffinity.Orange), PMove.gainBlock(PCLCardTarget.Team, 5).setUpgrade(1));
    }
}
