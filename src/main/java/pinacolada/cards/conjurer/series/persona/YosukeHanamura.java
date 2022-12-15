package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.base.moves.PMove_ReshuffleToTop;

public class YosukeHanamura extends PCLCard
{
    public static final PCLCardData DATA = register(YosukeHanamura.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(3, 1, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.persona);

    public YosukeHanamura()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PMove.draw(2).setUpgrade(1).setAffinity(PCLAffinity.Red, PCLAffinity.Green), new PMove_ReshuffleToTop(2, PCLCardGroupHelper.Hand).setUpgrade(1).setAlt2(true));
        addUseMove(PCond.starter(), PTrait.hasDamage(2));
    }
}
