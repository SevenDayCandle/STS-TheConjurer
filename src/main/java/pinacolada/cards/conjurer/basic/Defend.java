package pinacolada.cards.conjurer.basic;

import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.resources.conjurer.ConjurerResources;

@VisibleCard
public class Defend extends PCLCard
{
    public static final PCLCardData DATA = register(Defend.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.BASIC, PCLCardTarget.Self)
            .setBlock(5, 3)
            .setDefend();

    public Defend()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addBlockMove();
    }
}