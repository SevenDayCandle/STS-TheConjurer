package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Permafrost extends PCLCard
{
    public static final PCLCardData DATA = register(Permafrost.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public Permafrost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onOtherCardPlayed(PCLAffinity.Blue), PMove.applyToRandom(1, PCLElementHelper.Gelus).setUpgrade(1)));
    }
}
