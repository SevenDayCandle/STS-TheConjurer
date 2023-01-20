package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class AtmosphericPressure extends PCLCard
{
    public static final PCLCardData DATA = register(AtmosphericPressure.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public AtmosphericPressure()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Gelus, PCLElementHelper.Aer, PCLElementHelper.Petra));
    }
}
