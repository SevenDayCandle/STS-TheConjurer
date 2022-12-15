package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

public class Aitvaras extends PCLCard
{
    public static final PCLCardData DATA = register(Aitvaras.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(6, array(3, 0))
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Aitvaras()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(new PMod_PerReaction(3).setExtra(8).setUpgrade(0, -1).setUpgradeExtra(0, 2), PMove.applyToSingle(1, PCLPowerHelper.Burning));
        addUseMove(PCond.redox(), PTrait.hasDamage(3));
    }
}
