package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Strike extends PCLCard {
    public static final PCLCardData DATA = register(Strike.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.BASIC)
            .setDamage(6, 3)
            .setStrike();

    public Strike() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
    }
}