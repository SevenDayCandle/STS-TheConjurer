package pinacolada.cards.conjurer.colorless;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Crystallize extends PCLCard
{
    public static final PCLCardData DATA = register(Crystallize.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue)
            .setColorless();

    public Crystallize()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.retain(1), PMove.modifyBlock(2).setUpgrade(1).useParent(true));
    }
}
