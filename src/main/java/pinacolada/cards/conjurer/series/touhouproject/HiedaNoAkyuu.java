package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.base.conditions.PCond_CheckLevel;
import pinacolada.skills.skills.base.moves.PMove_ModifyTag;
import pinacolada.skills.skills.base.moves.PMove_ReshuffleToTop;

public class HiedaNoAkyuu extends PCLCard
{
    public static final PCLCardData DATA = register(HiedaNoAkyuu.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE, PCLCardTarget.Self)
            .setCostUpgrades(-1)
            .setBlock(3, 0)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public HiedaNoAkyuu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_ReshuffleToTop(2, PCLCardGroupHelper.DiscardPile, PCLCardGroupHelper.DrawPile));
        addUseMove(PCond.limited(), new PCond_CheckLevel(4, PCLAffinity.Orange),
                new PMove_ModifyTag(2, PCLCardTag.Ethereal).setExtra(-1).setCardGroup(PCLCardGroupHelper.DrawPile));
    }
}
