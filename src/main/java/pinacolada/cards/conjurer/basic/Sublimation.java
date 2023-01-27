package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

@VisibleCard
public class Sublimation extends PCLCard
{
    public static final PCLCardData DATA = register(Sublimation.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Red, PCLAffinity.Blue, PCLAffinity.Purple)
            .setCore();

    public Sublimation()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(CCond.redox(), PMove.dealDamage(5).setUpgrade(2)));
    }
}
