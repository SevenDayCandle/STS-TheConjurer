package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class FoliarTorque extends PCLCard
{
    public static final PCLCardData DATA = register(FoliarTorque.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.COMMON, PCLCardTarget.RandomEnemy)
            .setAffinities(2, PCLAffinity.Green)
            .setCore();

    public FoliarTorque()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.cyclePer(2).setUpgrade(1), PMove.applyToRandom(2, PCLElementHelper.Aer));
    }
}
