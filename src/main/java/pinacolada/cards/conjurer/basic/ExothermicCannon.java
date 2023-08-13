package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.modifiers.PMod_PerMatter;

@VisibleCard
public class ExothermicCannon extends PCLCard {
    public static final PCLCardData DATA = register(ExothermicCannon.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(9, 3)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public ExothermicCannon() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.BLOW04);
        addUseMove(new PMod_PerMatter(4).setExtra(6).setUpgrade(0, -1).setUpgradeExtra(0, 2), PMove.applyToSingle(1, PCLElementHelper.Ignis));
    }
}
