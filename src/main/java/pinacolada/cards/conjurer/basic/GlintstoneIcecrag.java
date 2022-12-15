package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class GlintstoneIcecrag extends PCLCard
{
    public static final PCLCardData DATA = register(GlintstoneIcecrag.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Magical)
            .setDamage(7, 2)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public GlintstoneIcecrag()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.ICE);
        addUseMove(PMove.applyToSingle(3, PCLPowerHelper.Chilled).setUpgrade(1));
    }
}
