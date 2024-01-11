package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class StrikeO extends PCLCard {
    public static final PCLCardData DATA = register(StrikeO.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.BASIC)
            .setAffinities(PCLAffinity.Orange)
            .setDamage(6, 3)
            .setStrike()
            .setCore();

    public StrikeO() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
    }
}