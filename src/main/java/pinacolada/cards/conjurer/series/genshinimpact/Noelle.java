package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Noelle extends PCLCard
{
    public static final PCLCardData DATA = register(Noelle.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON)
            .setBlock(8, 3)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Noelle()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToSingle(3, PCLPowerHelper.Stoned));
        addUseMove(PCond.combust(), PMove.retain(1));
    }
}
