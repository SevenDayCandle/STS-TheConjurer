package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.conjurer.colorless.BlazingHeat;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.base.conditions.PCond_CheckPower;

public class Baphomet extends PCLCard
{
    public static final PCLCardData DATA = register(Baphomet.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Dark)
            .setTags(PCLCardTag.Delayed)
            .setUTags(PCLCardTag.Retain)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Baphomet()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(new PCond_CheckPower(PCLCardTarget.AllEnemy, 1, PCLPowerHelper.Burning), PMultiSkill.join(new PMove_GainReaction(4).setUpgrade(2), PMove.applyToEnemies(1, PCLPowerHelper.Shackles))));
        addUseMove(PCond.limited(), new PCond_CheckPower(PCLCardTarget.AllEnemy, 8, PCLPowerHelper.Burning), PMove.playCopy(1, PCLCardTarget.Self, BlazingHeat.DATA.ID));
    }
}
