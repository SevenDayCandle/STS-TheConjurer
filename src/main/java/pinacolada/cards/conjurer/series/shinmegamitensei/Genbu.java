package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.modifiers.PMod_HighestAffinityBranch;

public class Genbu extends PCLCard
{
    public static final PCLCardData DATA = register(Genbu.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setBlock(5, 1, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Genbu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMod_HighestAffinityBranch(PCLAffinity.Green, PCLAffinity.Blue),
                PMultiSkill.join(
                        PMove.motivate(1),
                        PMove.gainTemporary(5, PCLPowerHelper.Resistance).setUpgrade(1)
                ));
    }
}
