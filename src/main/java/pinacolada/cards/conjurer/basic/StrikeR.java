package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class StrikeR extends PCLCard
{
    public static final PCLCardData DATA = register(StrikeR.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.BASIC)
            .setAffinities(PCLAffinity.Red)
            .setDamage(6, 3);

    public StrikeR()
    {
        super(DATA);

        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_DIAGONAL);
    }
}