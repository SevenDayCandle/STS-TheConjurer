package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

@VisibleCard
public class ExothermicCannon extends PCLCard
{
    public static final PCLCardData DATA = register(ExothermicCannon.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(9, 3)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public ExothermicCannon()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(new PMod_PerReaction(3).setExtra(8).setUpgrade(0, -1).setUpgradeExtra(0, 2), PMove.applyToSingle(1, PCLElementHelper.Ignis));
    }
}
