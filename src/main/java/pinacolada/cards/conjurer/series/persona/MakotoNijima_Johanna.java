package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_PerCard;

public class MakotoNijima_Johanna extends PCLCard
{
    public static final PCLCardData DATA = register(MakotoNijima_Johanna.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Orange)
            .setTags(PCLCardTag.Retain, PCLCardTag.Haste, PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.persona);

    public MakotoNijima_Johanna()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_PerCard(1, PCLCardGroupHelper.Hand).setAffinity(PCLAffinity.Orange), PMove.channelOrb(1, PCLOrbHelper.Earth).setUpgradeExtra(1));
        addUseMove(PMultiSkill.choose(
                PMove.obtain(MakotoNijima.DATA),
                PMove.triggerOrb(2, 0, PCLOrbHelper.Earth)
        ));
    }
}
