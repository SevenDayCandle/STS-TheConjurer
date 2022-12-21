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
            .setSkill(1, CardRarity.COMMON)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public Condensation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.triggerAlly(PCLCardTarget.SingleAlly, 3));
        addUseMove(PMove.applyToSingle(2, PCLElementHelper.Chilled, PCLPowerHelper.Weak));
    }
}
