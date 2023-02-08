package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class PoisonSpore extends PCLCard
{
    public static final PCLCardData DATA = register(PoisonSpore.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setAffinities(1, PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public PoisonSpore()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToSingle(3, PCLPowerHelper.Poison, PCLElementHelper.Aer).setUpgrade(2));
    }
}
