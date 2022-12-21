package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class Overheat extends PCLCard
{
    public static final PCLCardData DATA = register(Overheat.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public Overheat()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.gain(1, PCLPowerHelper.Vulnerable));
        addUseMove(PMove.apply(PCLCardTarget.Team,5).setUpgrade(2));
    }
}
