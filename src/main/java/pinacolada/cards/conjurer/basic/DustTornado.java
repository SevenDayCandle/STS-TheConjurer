package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class DustTornado extends PCLCard
{
    public static final PCLCardData DATA = register(DustTornado.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public DustTornado()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PCond.exhaust(1), PMove.summon(1, PCLCardGroupHelper.DiscardPile));
        addUseMove(CCond.redox(), CMove.applyElementToEnemies(5, PCLAffinity.Green));
    }
}
