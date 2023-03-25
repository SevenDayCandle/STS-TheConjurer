package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Preservation extends PCLCard
{
    public static final PCLCardData DATA = register(Preservation.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setCore();

    public Preservation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.retain(2).setUpgrade(1));
        addUseMove(PMod.bonusPerLevel(2, PCLAffinity.Orange).setUpgrade(1), PMove.gainBlock(PCLCardTarget.Team, 4));
    }
}
