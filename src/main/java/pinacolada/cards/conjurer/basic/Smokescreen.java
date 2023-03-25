package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Smokescreen extends PCLCard
{
    public static final PCLCardData DATA = register(Smokescreen.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(10, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public Smokescreen()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addBlockMove();
        addUseMove(PMultiCond.ifElse(PMove.gain(2, PCLElementHelper.Ignis, PCLElementHelper.Aer), PMove.selfExhaust(), PCond.exhaust(1, PCLCardGroupHelper.Hand)));
    }
}
