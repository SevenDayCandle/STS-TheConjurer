package pinacolada.resources.conjurer;

import pinacolada.resources.loadout.PCLLoadout;

public class ConjurerLoadout extends PCLLoadout
{
    public static ConjurerLoadout generate(String prefix)
    {
        return new ConjurerLoadout(ConjurerResources.conjurer.createID(prefix));
    }

    public ConjurerLoadout()
    {
        this(createFullID(ConjurerLoadout.class), -1);
    }

    public ConjurerLoadout(String id)
    {
        this(id, 0);
    }

    public ConjurerLoadout(String id, int unlockLevel)
    {
        super(ConjurerEnum.Cards.THE_CONJURER, id, unlockLevel);
    }
}
