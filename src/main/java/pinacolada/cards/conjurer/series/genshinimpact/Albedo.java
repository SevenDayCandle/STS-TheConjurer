package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_CheckOrb;
import pinacolada.skills.skills.base.moves.PMove_StabilizePower;

public class Albedo extends PCLCard
{
    public static final PCLCardData DATA = register(Albedo.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.UNCOMMON)
            .setBlock(10, 3)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Albedo()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(new PMove_StabilizePower(PCLCardTarget.Single, 1, PCLPowerHelper.Chilled, PCLPowerHelper.Stoned));
        addUseMove(new PCond_CheckOrb(3, PCLOrbHelper.Earth), PMove.gain(1, PCLPowerHelper.Innovation));
    }
}
