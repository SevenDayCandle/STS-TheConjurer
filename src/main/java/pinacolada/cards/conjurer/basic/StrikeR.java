package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class StrikeR extends PCLCard {
    public static final PCLCardData DATA = register(StrikeR.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.BASIC)
            .setAffinities(PCLAffinity.Red)
            .setDamage(6, 3)
            .setStrike()
            .setCore();

    public StrikeR() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
    }
}