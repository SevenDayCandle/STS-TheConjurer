package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PDelay;
import pinacolada.skills.skills.base.modifiers.PMod_SelectPerCard;
import pinacolada.skills.skills.base.moves.PMove_Obtain;
import pinacolada.skills.skills.base.moves.PMove_Play;

public class KasumiYoshizawa extends PCLCard
{
    public static final PCLCardData DATA = register(KasumiYoshizawa.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.RARE)
            .setBlock(2, 5)
            .setTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.persona);

    public KasumiYoshizawa()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PDelay.turnStartLast(1), new PMove_Play(2, PCLCardTarget.Single, PCLCardGroupHelper.Hand));
        addUseMove(PCond.pileHas(3, PCLCardGroupHelper.Hand).setAffinity(PCLAffinity.Green),
                new PMod_SelectPerCard(1, PCLCardGroupHelper.Hand).setCardTypes(CardType.ATTACK),
                new PMove_Obtain().useParent(true));
    }
}
