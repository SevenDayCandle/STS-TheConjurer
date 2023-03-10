package pinacolada.cards.conjurer.colorless;


import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardSelection;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.misc.PCLUseInfo;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class OracleSphere extends PCLCard
{
    public static final PCLCardData DATA = register(OracleSphere.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.RARE)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore(true);

    public OracleSphere()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 5).setUpgrade(1);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.reshuffleDiscardPile(false);
        PCLActions.bottom.reshuffleFromPile(name, move.amount, player.discardPile).setDestination(PCLCardSelection.Top);
    }
}
