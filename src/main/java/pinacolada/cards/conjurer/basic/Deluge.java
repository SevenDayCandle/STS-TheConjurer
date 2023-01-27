package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Deluge extends PCLCard
{
    public static final PCLCardData DATA = register(Deluge.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Team)
            .setCostUpgrades(-1)
            .setBlock(9, 0)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public Deluge()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.obtainDiscardPile(2, Condensation.DATA));
    }
}
