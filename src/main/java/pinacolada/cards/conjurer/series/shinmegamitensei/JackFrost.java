package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;

public class JackFrost extends PCLCard
{
    public static final PCLCardData DATA = register(JackFrost.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setBlock(2, 2)
            .setAffinities(2, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public JackFrost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToSingle(3, PCLPowerHelper.Chilled));
        addUseMove(new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Burning), new PMove_GainReaction(2).setUpgrade(1));
    }
}
