package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class Condensation extends PCLCard
{
    public static final PCLCardData DATA = register(Condensation.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public Condensation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToSingle(1, PCLElementHelper.Chilled, PCLPowerHelper.Weak));
        addUseMove(PMove.triggerAlly(PCLCardTarget.SingleAlly, 2).setUpgrade(1));
    }
}
