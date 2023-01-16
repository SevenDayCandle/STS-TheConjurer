package pinacolada.cards.conjurer.basic;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

public class DefendG extends PCLCard
{
    public static final PCLCardData DATA = register(DefendG.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.BASIC, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Green)
            .setBlock(5, 3);

    public DefendG()
    {
        super(DATA);

        this.tags.add(CardTags.STARTER_DEFEND);
    }
}