package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class RyujiSakamoto extends PCLCard
{
    public static final PCLCardData DATA = register(RyujiSakamoto.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(8, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.persona);

    public RyujiSakamoto()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY);
        addUseMove(PCond.cooldown(2), PMove.selfTransform(RyujiSakamoto_CaptainKidd.DATA));
        addUseMove(PCond.redox(), PMove.gain(2, PCLPowerHelper.SupportDamage));
    }
}
