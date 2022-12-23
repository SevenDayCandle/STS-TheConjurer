package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.special.moves.PMove_RestoreCardHP;

public class NaturalSelection extends PCLCard
{
    public static final PCLCardData DATA = register(NaturalSelection.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.SingleAlly)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public NaturalSelection()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.withdrawAlly(1), PMultiSkill.join(new PMove_RestoreCardHP(1, 12).setUpgradeExtra(5), PMove.modifyDamage(2).setUpgrade(1)).useParent(true));
    }
}
