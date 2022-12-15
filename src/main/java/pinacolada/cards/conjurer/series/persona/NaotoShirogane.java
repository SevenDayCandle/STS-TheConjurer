package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

public class NaotoShirogane extends PCLCard
{
    public static final PCLCardData DATA = register(NaotoShirogane.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Ranged)
            .setDamage(9, 3, 1)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.persona);

    public NaotoShirogane()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.GUNSHOT);
        addUseMove(PCond.exhaust(1, PCLCardGroupHelper.Hand), PMove.applyToSingle(3, PCLPowerHelper.LockOn));
        addUseMove(PCond.cooldown(3), PMultiSkill.join(PMove.selfExhaust(), PMove.triggerOrb(3, 0)));
    }
}
