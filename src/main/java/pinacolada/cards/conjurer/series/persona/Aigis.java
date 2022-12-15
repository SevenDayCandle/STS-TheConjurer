package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_HavePlayed;

public class Aigis extends PCLCard
{
    public static final PCLCardData DATA = register(Aigis.class, ConjurerResources.conjurer)
            .setSkill(1, CardRarity.UNCOMMON)
            .setBlock(17, 4)
            .setTags(PCLCardTag.Exhaust, PCLCardTag.Haste)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.persona);

    public Aigis()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.gain(2, PCLPowerHelper.DrawLess, PCLPowerHelper.Stoned));
        addUseMove(new PCond_HavePlayed(2).setAffinity(PCLAffinity.Orange), PMove.gain(1, PCLPowerHelper.Blur));
    }
}
