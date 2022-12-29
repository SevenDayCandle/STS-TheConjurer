package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class Lithosphere extends PCLCard
{
    public static final PCLCardData DATA = register(Lithosphere.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(7, 2)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public Lithosphere()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToSingle(3, PCLElementHelper.Stoned).setUpgrade(1));
    }
}