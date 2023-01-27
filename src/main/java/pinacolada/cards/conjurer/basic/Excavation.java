package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Excavation extends PCLCard
{
    public static final PCLCardData DATA = register(Excavation.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setBlock(9, 3)
            .setAffinities(PCLAffinity.Orange)
            .setCore();

    public Excavation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.fetch(2, PCLCardGroupHelper.DiscardPile).edit(f -> f.setAffinity(PCLAffinity.Green, PCLAffinity.Orange)), PMove.retain(2).edit(f -> f.setForced(true)).useParent(true));
    }
}
