package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class AtmosphericPressure extends PCLCard
{
    public static final PCLCardData DATA = register(AtmosphericPressure.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public AtmosphericPressure()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.draw(1).setUpgrade(1));
        addUseMove(PMove.stabilize(PCLCardTarget.AllEnemy, PCLElementHelper.Gelus, PCLElementHelper.Aer));
    }
}
