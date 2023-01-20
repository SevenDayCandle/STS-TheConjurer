package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class AirCurrent extends PCLCard
{
    public static final PCLCardData DATA = register(AirCurrent.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(2, 2)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public AirCurrent()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WIND);
        addUseMove(PMove.applyToSingle(3, PCLElementHelper.Aer).setUpgrade(1));
    }
}
