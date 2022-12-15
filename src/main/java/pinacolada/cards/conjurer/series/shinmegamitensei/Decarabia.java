package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.moves.PMove_ModifyAffinity;

public class Decarabia extends PCLCard
{
    public static final PCLCardData DATA = register(Decarabia.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.AllEnemy)
            .setMagicNumber(4, 2)
            .setTags(
                    PCLCardTag.Exhaust.make(1),
                    PCLCardTag.Retain.make(1, array(-1, 1))
            )
            .setAffinities(PCLAffinity.Blue)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Decarabia()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_ModifyAffinity(2, 1, PCLAffinity.Red, PCLAffinity.Blue).setCardGroup(PCLCardGroupHelper.Hand).setAlt2(true).setUpgradeExtra(0, 1));
        addUseMove(PCond.checkLevel(3, PCLAffinity.Red, PCLAffinity.Blue).setAlt(true), PMove.retain(2));
    }
}
