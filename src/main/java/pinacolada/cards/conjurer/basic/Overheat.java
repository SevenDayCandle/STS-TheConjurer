package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class Overheat extends PCLCard
{
    public static final PCLCardData DATA = register(Overheat.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public Overheat()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.apply(PCLCardTarget.Team,5, PCLPowerHelper.Vigor, PCLElementHelper.Ignis).setUpgrade(2));
        addUseMove(PMod.perCreature(PCLCardTarget.AllAlly, 1), PMove.takeDamage(1));
    }
}
