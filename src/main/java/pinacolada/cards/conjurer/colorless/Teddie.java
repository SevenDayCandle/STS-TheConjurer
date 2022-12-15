package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_CheckPower;

public class Teddie extends PCLCard
{
    public static final PCLCardData DATA = register(Teddie.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Star)
            .setTags(
                    PCLCardTag.Innate.make(0, array(1, 0)),
                    PCLCardTag.Loyal.make(0, array(1, 0))
            )
            .setMultiformData(2)
            .setColorless()
            .setLoadout(ConjurerPlayerData.persona);

    public Teddie()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(new PCond_CheckPower().setTarget(PCLCardTarget.Self).setAlt2(true), PMove.gainTempHP(4).setUpgrade(1)));
        addGainPower(PTrigger.interactable(PCond.payEnergy(1), PMove.gain(2).setAlt2(true)));
    }
}