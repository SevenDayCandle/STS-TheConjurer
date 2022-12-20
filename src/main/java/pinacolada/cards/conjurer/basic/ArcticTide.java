package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PMove;

public class ArcticTide extends PCLCard
{
    public static final PCLCardData DATA = register(ArcticTide.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(12, 2)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public ArcticTide()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WATER);
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Vulnerable).setUpgrade(1));
    }
}
