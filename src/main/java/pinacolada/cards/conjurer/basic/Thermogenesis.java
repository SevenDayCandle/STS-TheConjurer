package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
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
        addUseMove(PCond.onDraw(), PMove.applyToRandom(2, PCLElementHelper.Ignis));
    }
}
