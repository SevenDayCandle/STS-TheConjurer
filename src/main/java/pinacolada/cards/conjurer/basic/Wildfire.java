package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Wildfire extends PCLCard
{
    public static final PCLCardData DATA = register(Wildfire.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(12, 0)
            .setRTags(PCLCardTag.Exhaust)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public Wildfire()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addGainPower(3, PTrigger.when(PCond.onTurnStart(), PMove.applyToEnemies(1, PCLElementHelper.Burned)));
    }
}
