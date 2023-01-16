package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

public class DefendO extends PCLCard
{
    public static final PCLCardData DATA = register(DefendO.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.BASIC, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Orange)
            .setBlock(5, 3);

    public DefendO()
    {
        super(DATA);

        this.tags.add(CardTags.STARTER_DEFEND);
    }
}