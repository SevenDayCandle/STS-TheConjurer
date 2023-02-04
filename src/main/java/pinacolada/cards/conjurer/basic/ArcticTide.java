package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class ArcticTide extends PCLCard
{
    public static final PCLCardData DATA = register(ArcticTide.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(7, 3)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public ArcticTide()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.WATER);
        addUseMove(PMove.applyToEnemies(2, PCLElementHelper.Gelus, PCLPowerHelper.Vulnerable));
    }
}
