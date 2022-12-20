package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;

public class Strike extends PCLCard
{
    public static final PCLCardData DATA = register(Strike.class, ConjurerResources.conjurer).setAttack(1, CardRarity.BASIC)
            .setDamage(6, 3);

    public Strike()
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