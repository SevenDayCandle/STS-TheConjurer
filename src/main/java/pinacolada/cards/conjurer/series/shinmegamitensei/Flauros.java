package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Flauros extends PCLCard
{
    public static final PCLCardData DATA = register(Flauros.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(5, 1)
            .setPriority(1)
            .setHp(8, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Flauros()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(PCond.onWithdraw(), PMove.applyToEnemies(3, PCLPowerHelper.Vulnerable));
    }
}
