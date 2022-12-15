package pinacolada.cards.conjurer.colorless;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.orbs.pcl.Chaos;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;

public class Wish extends PCLCard
{
    public static final PCLCardData DATA = register(Wish.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Star)
            .setTags(PCLCardTag.Purge.make(), PCLCardTag.Haste.make(1, -1))
            .setColorless();

    public Wish()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.channelOrb(1, PCLOrbHelper.Chaos));
        addSpecialMove(0, this::action, 1);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        Chaos.chooseMoves();
    }
}
