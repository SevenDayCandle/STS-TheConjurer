package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;

public class RazorWind extends PCLCard
{
    public static final PCLCardData DATA = register(RazorWind.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(5, 0)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public RazorWind()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND);
        addUseMove(CMove.applyElementToEnemies(3, PCLAffinity.Green).setUpgrade(1));
        addUseMove(PCond.onDiscard(), CMove.applyElementToEnemies(3, PCLAffinity.Green));
    }
}
