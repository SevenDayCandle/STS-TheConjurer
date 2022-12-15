package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Sucrose extends PCLCard
{
    public static final PCLCardData DATA = register(Sucrose.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON)
            .setMagicNumber(1, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Sucrose()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.applyToSingle(1, PCLPowerHelper.LockOn).setUpgrade(1));
        addUseMove(PCond.redox(), PMove.applyToSingle(3, PCLPowerHelper.Flowed));
    }
}
