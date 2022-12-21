package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class HydraulicGeneration extends PCLCard
{
    public static final PCLCardData DATA = register(HydraulicGeneration.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setCore();

    public HydraulicGeneration()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMod.cyclePer(3), PMultiSkill.join(CMove.gainReaction(3).setUpgrade(1), PMove.applyToRandom(3, PCLElementHelper.Chilled).setUpgrade(1)));
    }
}
