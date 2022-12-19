package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Thermogenesis extends PCLCard
{
    public static final PCLCardData DATA = register(Thermogenesis.class, ConjurerResources.conjurer)
            .setAttack(1,CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(6, 3)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public Thermogenesis()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(PCond.onDraw(), CMove.applyElementToRandom(2, PCLAffinity.Red));
    }
}
