package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class PowderSnow extends PCLCard
{
    public static final PCLCardData DATA = register(PowderSnow.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(2, 2)
            .setAffinities(PCLAffinity.Blue)
            .setCore();

    public PowderSnow()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.ICE);
        addUseMove(PMove.applyToSingle(3, PCLElementHelper.Chilled).setUpgrade(1));
    }
}
