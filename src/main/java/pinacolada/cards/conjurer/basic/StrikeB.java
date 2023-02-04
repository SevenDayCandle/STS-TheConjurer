package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class StrikeB extends PCLCard
{
    public static final PCLCardData DATA = register(StrikeB.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.BASIC)
            .setAffinities(PCLAffinity.Blue)
            .setDamage(6, 3);

    public StrikeB()
    {
        super(DATA);

        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.SLASH_DIAGONAL);
    }
}