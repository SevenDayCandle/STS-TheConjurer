package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;

public class MitsuruKirijou extends PCLCard
{
    public static final PCLCardData DATA = register(MitsuruKirijou.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setMaxCopies(3)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.persona);

    public MitsuruKirijou()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.combust(), PMove.gain(1, PCLPowerHelper.CounterAttack).setUpgrade(1)));
        addGainPower(PTrigger.interactable(new PCond_PayReaction(10), PMove.gainBlock(9)));
    }
}
