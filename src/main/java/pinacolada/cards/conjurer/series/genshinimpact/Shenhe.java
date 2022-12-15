package pinacolada.cards.conjurer.series.genshinimpact;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;

public class Shenhe extends PCLCard
{
    public static final PCLCardData DATA = register(Shenhe.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.UNCOMMON)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setRTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Shenhe()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(PCond.onOtherCardPlayed().setAffinity(PCLAffinity.Blue), PMove.applyToRandom(2, PCLPowerHelper.Chilled)));
        addGainPower(PTrigger.interactable(PCond.exhaust(1, PCLCardGroupHelper.Hand).setAlt(true), PMove.applyToEnemies(4, PCLPowerHelper.Chilled)));
    }
}