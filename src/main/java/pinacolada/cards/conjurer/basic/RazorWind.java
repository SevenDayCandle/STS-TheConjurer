package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class RazorWind extends PCLCard
{
    public static final PCLCardData DATA = register(RazorWind.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.AllEnemy)
            .setDamage(5, 2)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public RazorWind()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND);
        addUseMove(PCond.cycle(1), PMove.applyToEnemies(3, PCLElementHelper.Flowed).setUpgrade(1));
    }
}
