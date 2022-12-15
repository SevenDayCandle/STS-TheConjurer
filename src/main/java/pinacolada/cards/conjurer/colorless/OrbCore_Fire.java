package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_IfIsOrb;
import pinacolada.skills.skills.base.modifiers.PMod_PerAffinityLevel;
import pinacolada.skills.skills.base.traits.PTrait_OrbFocus;

public class OrbCore_Fire extends PCLCard
{
    public static final PCLCardData DATA = register(OrbCore_Fire.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Red)
            .setColorless();

    public OrbCore_Fire()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.passive(new PCond_IfIsOrb(PCLOrbHelper.Fire), new PMod_PerAffinityLevel(1, PCLAffinity.Red), new PTrait_OrbFocus(1).setUpgrade(1)));
        addGainPower(PTrigger.interactable(PCond.payEnergy(1), PMove.channelOrb(1, PCLOrbHelper.Fire)));
    }
}