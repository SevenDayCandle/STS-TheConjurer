package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Phytotoxin extends PCLCard
{
    public static final PCLCardData DATA = register(Phytotoxin.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.UNCOMMON)
            .setAffinities(1, PCLAffinity.Green, PCLAffinity.Orange)
            .setCore();

    public Phytotoxin()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.takeDamage(), PMove.applyToEnemies(2, PCLElementHelper.Aer, PCLPowerHelper.Poison).setUpgrade(1)));
    }
}
