package pinacolada.cards.conjurer.series.touhouproject;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PMultiSkill;

public class KonpakuYoumu extends PCLCard
{
    public static final PCLCardData DATA = register(KonpakuYoumu.class, ConjurerResources.conjurer)
            .setSkill(-1, CardRarity.COMMON, PCLCardTarget.Self)
            .setBlock(3, 1)
            .setAffinities(PCLAffinity.Green)
            .setTags(PCLCardTag.Exhaust)
            .setUTags(PCLCardTag.Retain)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public KonpakuYoumu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.payXEnergy(), PMultiSkill.join(PMove.scry(1), PMove.gain(1, PCLPowerHelper.Energized)));
        addUseMove(PCond.delegate(PSkill.Delegate.Discard, PSkill.Delegate.Reshuffle), PMove.gain(1, PCLPowerHelper.NextTurnDraw));
    }
}
