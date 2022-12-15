package pinacolada.cards.conjurer.series.genshinimpact;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.cards.conjurer.colorless.ErodingTerra;
import pinacolada.orbs.PCLOrbHelper;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;

public class Zhongli extends PCLCard
{
    public static final PCLCardData DATA = register(Zhongli.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setTags(PCLCardTag.Delayed)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Zhongli()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addUseMove(PMove.gain(4, PCLPowerHelper.Resistance).setUpgrade(2), getApplyPower(PCLCardTarget.Self, -1, PTrigger.when(PCond.onTurnStart(), PMove.triggerOrb(1, 0, PCLOrbHelper.Earth))));
        addGainPower(PTrigger.interactablePerCombat(1, new PCond_PayReaction(70), PMove.playCopy(1, PCLCardTarget.Self, ErodingTerra.DATA.ID)));
    }
}