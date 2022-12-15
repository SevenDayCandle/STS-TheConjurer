package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.conjurer.colorless.SheerCold;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.conditions.PCond_TriggerOrb;

public class KingFrost extends PCLCard
{
    public static final PCLCardData DATA = register(KingFrost.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(PCLAffinity.Blue)
            .setTags(PCLCardTag.Delayed)
            .setUTags(PCLCardTag.Retain)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public KingFrost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(new PCond_TriggerOrb(1), PMultiSkill.join(new PMove_GainReaction(2).setUpgrade(1), PMove.applyToEnemies(1, PCLPowerHelper.Chilled).setUpgrade(1))));
        addUseMove(PCond.limited(), PCond.pileHas(15, PCLCardGroupHelper.ExhaustPile).setAffinity(PCLAffinity.Blue), PMove.playCopy(1, PCLCardTarget.Self, SheerCold.DATA.ID));
    }
}
