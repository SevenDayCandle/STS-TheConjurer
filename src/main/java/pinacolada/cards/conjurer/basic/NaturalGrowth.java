package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class NaturalGrowth extends PCLCard
{
    public static final PCLCardData DATA = register(NaturalGrowth.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.SingleAlly)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public NaturalGrowth()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.withdrawAlly(1), PMultiSkill.join(PMove.modifyBlock(2), PMove.modifyDamage(2)).useParent(true));
    }
}
