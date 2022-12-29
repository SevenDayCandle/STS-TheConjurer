package pinacolada.cards.conjurer.colorless;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrigger;

public class Radioactivity extends PCLCard
{
    public static final PCLCardData DATA = register(Radioactivity.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setColorless();

    public Radioactivity()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.apply))
    }
}
