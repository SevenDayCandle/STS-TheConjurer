package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;

@VisibleCard
public class ColossalFormation extends PCLCard
{
    public static final PCLCardData DATA = register(ColossalFormation.class, ConjurerResources.conjurer)
            .setSkill(3, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(13, 1)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public ColossalFormation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addBlockMove().setBonus(CMod.perReaction(4).setUpgrade(-1), 1);
    }
}
