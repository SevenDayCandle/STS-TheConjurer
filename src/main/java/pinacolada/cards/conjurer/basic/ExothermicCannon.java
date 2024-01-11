package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class ExothermicCannon extends PCLCard {
    public static final PCLCardData DATA = register(ExothermicCannon.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(9, 3)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public ExothermicCannon() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD24);
        addUseMove(CCond.react(), PMove.modifyDamage(3));
    }
}
