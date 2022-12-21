package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class Ignition extends PCLCard
{
    public static final PCLCardData DATA = register(Ignition.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setCostUpgrades(-1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public Ignition()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.triggerAlly(PCLCardTarget.AllAlly, 2));
        addUseMove(PMove.apply(PCLCardTarget.Team, 3, PCLElementHelper.Burning));
    }
}
