package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;

@VisibleCard
public class BoilingPoint extends PCLCard
{
    public static final PCLCardData DATA = register(BoilingPoint.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(9, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public BoilingPoint()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.NONE).setDamageEffect(PCLEffekseerEFX.SWORD18);
        addUseMove(CCond.combust(), PMove.applyToSingle(2, PCLPowerHelper.Vulnerable, PCLElementHelper.Gelus).setUpgrade(1));
    }
}
