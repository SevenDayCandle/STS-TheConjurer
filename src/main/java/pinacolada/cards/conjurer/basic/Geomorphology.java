package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

@VisibleCard
public class Geomorphology extends PCLCard
{
    public static final PCLCardData DATA = register(Geomorphology.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public Geomorphology()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onSummon(), PMove.applyToRandom(2, PCLElementHelper.Petra).setUpgrade(1)));
    }
}
