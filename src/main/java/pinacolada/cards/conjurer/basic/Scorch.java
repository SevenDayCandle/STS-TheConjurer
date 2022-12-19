package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;

public class Scorch extends PCLCard
{
    public static final PCLCardData DATA = register(Scorch.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(2, 2)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public Scorch()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(CMove.applyElementToSingle(3, PCLAffinity.Red).setUpgrade(1));
    }
}
