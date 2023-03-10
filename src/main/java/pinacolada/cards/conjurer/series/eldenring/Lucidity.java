package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMod;

@VisibleCard
public class Lucidity extends PCLCard
{
    public static final PCLCardData DATA = register(Lucidity.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.COMMON, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public Lucidity()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.bonusPerLevel(4, PCLAffinity.Blue), CMove.gainReaction(11).setUpgrade(4));
    }
}
