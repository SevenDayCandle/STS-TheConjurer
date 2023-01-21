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

@VisibleCard
public class FoliarTorque extends PCLCard
{
    public static final PCLCardData DATA = register(FoliarTorque.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.AllEnemy)
            .setAffinities(2, PCLAffinity.Green)
            .setCore();

    public FoliarTorque()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.cyclePer(2).setUpgrade(1).edit(f -> f.setType(CardType.ATTACK)), PMove.applyToEnemies(2, PCLElementHelper.Aer).setUpgrade(1));
    }
}
