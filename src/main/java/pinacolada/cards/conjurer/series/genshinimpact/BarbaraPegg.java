package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class BarbaraPegg extends PCLCard
{
    public static final PCLCardData DATA = register(BarbaraPegg.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON, PCLCardTarget.Self)
            .setHp(5, 3)
            .setTags(PCLCardTag.Purge)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public BarbaraPegg()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.channelOrb(1, PCLOrbHelper.Water).setExtra(2));
        addUseMove(PCond.limited(), PCond.combust(), PMove.gain(1, PCLPowerHelper.Vitality));
    }
}
