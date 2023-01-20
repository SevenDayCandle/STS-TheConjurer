package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PDelay;

@VisibleCard
public class OceanSubmersion extends PCLCard
{
    public static final PCLCardData DATA = register(OceanSubmersion.class, ConjurerResources.conjurer)
            .setSkill(2, CardRarity.RARE, PCLCardTarget.Team)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public OceanSubmersion()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.bonusPerLevel(1, PCLAffinity.Blue).setUpgrade(1), PMove.gainTempHP(PCLCardTarget.Team, 6).setUpgrade(1));
        addUseMove(PDelay.turnStart(1), PMove.applyToEnemies(2, PCLElementHelper.Gelus));
    }
}
