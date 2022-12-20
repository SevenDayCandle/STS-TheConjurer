package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

public class ExothermicCannon extends PCLCard
{
    public static final PCLCardData DATA = register(ExothermicCannon.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(7, 3)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public ExothermicCannon()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(new PMod_PerReaction(3).setExtra(8).setUpgrade(0, -1).setUpgradeExtra(0, 2), CMove.applyElementToSingle(1, PCLAffinity.Red));
    }
}
