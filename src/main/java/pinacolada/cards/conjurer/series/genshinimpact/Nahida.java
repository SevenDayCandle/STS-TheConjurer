package pinacolada.cards.conjurer.series.genshinimpact;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_HighestAffinityBranch;

public class Nahida extends PCLCard
{
    public static final PCLCardData DATA = register(Nahida.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.SPECIAL)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setRTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Nahida()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onTurnStart(),
                new PMod_HighestAffinityBranch(PCLAffinity.Red, PCLAffinity.Green, PCLAffinity.Blue, PCLAffinity.Orange),
                PMultiSkill.join(
                        PMove.gainEnergy(1),
                        PMove.draw(1),
                        PMove.gainTemporary(2, PCLPowerHelper.Focus),
                        PMove.gainTemporary(4, PCLPowerHelper.Malleable)
                )));
    }
}