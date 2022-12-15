package pinacolada.cards.conjurer.series.persona;

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
import pinacolada.skills.PTrigger;
import pinacolada.skills.skills.PMultiSkill;

public class AnnTakamaki_Carmen extends PCLCard
{
    public static final PCLCardData DATA = register(AnnTakamaki_Carmen.class, ConjurerResources.conjurer)
            .setSkill(0, CardRarity.SPECIAL, PCLCardTarget.Self)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setTags(PCLCardTag.Retain, PCLCardTag.Exhaust)
            .setLoadout(ConjurerPlayerData.persona);

    public AnnTakamaki_Carmen()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(2, PTrigger.when(PCond.onOtherCardPlayed().setCardTypes(CardType.ATTACK), PMove.applyToEnemies(1, PCLPowerHelper.Burning)));
        addUseMove(PMultiSkill.choose(
                PMove.obtain(AnnTakamaki.DATA),
                PMove.channelOrb(2, PCLOrbHelper.Fire)
        ));
    }
}
