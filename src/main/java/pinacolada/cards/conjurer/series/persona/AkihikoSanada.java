package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrait;

public class AkihikoSanada extends PCLCard
{
    public static final PCLCardData DATA = register(AkihikoSanada.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.COMMON)
            .setDamage(9, 3)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.persona);

    public AkihikoSanada()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.PUNCH);
        addUseMove(PCond.ifElse(PTrait.hasDamage(5), PMove.applyToSingle(3, PCLPowerHelper.Vulnerable), PCond.checkPower(PCLCardTarget.Single, 1, PCLPowerHelper.Vulnerable)));
        addUseMove(PCond.cooldown(2), PTrait.hasDamageMultiplier(100));
    }
}
