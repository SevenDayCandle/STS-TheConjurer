package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.base.conditions.PCond_IfHasAffinity;
import pinacolada.skills.skills.base.traits.PTrait_DamageMultiplier;

public class Ose extends PCLCard
{
    public static final PCLCardData DATA = register(Ose.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(12, array(3, 0))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Ose()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HEAVY);
        addGainPower(1, PTrigger.passive(new PCond_IfHasAffinity(PCLAffinity.Red, PCLAffinity.Green), new PTrait_DamageMultiplier(50).setUpgrade(0, 25)));
        addUseMove(PCond.onDiscard(), PMove.gain(2, PCLPowerHelper.Vigor));
    }
}
