package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;
import pinacolada.skills.skills.base.moves.PMove_ModifyAffinity;
import pinacolada.skills.skills.base.moves.PMove_RemovePower;

public class HaruOkumura_Milady extends PCLCard
{
    public static final PCLCardData DATA = register(HaruOkumura_Milady.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.AllEnemy)
            .setMagicNumber(2, 3)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Light)
            .setTags(PCLCardTag.Retain, PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.persona);

    public HaruOkumura_Milady()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_RemovePower(PCLCardTarget.AllEnemy, PCLPowerHelper.Stoned), new PMod_PerPower(1, PCLPowerHelper.Stoned).setTarget(PCLCardTarget.AllEnemy), PTrait.hasTempHP(1));
        addUseMove(PMultiSkill.choose(
                PMove.obtain(HaruOkumura.DATA),
                new PMove_ModifyAffinity(PCLAffinity.Orange).setAmount(2).setCardGroup(PCLCardGroupHelper.DiscardPile)
        ));
    }
}
