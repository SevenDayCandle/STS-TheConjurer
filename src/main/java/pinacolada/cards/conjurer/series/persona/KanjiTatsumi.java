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
import pinacolada.skills.skills.PDelay;
import pinacolada.skills.skills.base.conditions.PCond_IsAttacking;

public class KanjiTatsumi extends PCLCard
{
    public static final PCLCardData DATA = register(KanjiTatsumi.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.COMMON)
            .setDamage(15, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.persona);

    public KanjiTatsumi()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_HEAVY);
        addUseMove(new PCond_IsAttacking(PCLCardTarget.Single), PMove.gainBlock(5).setUpgrade(2));
        addUseMove(PCond.cooldown(2), PDelay.turnStartLast(1), PMove.applyToEnemies(5, PCLPowerHelper.Stoned));
    }
}
