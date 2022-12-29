package pinacolada.cards.conjurer.colorless;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PMultiCond;

public class Radioactivity extends PCLCard
{
    public static final PCLCardData DATA = register(Radioactivity.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setColorless();

    public Radioactivity()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PMultiCond.or((PCond) PCond.checkPowerAoe(1).setAlt(true), PCond.checkPowerAoe(1)), PMove.loseHp(PCLCardTarget.RandomEnemy, 4).setUpgrade(2)));
    }
}
