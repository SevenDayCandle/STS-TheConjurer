package pinacolada.cards.conjurer.series.persona;

import pinacolada.actions.PCLActions;
import pinacolada.actions.orbs.ShuffleOrbs;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_Redox;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.PMultiSkill;
import pinacolada.skills.skills.PSpecialSkill;

public class RiseKujikawa extends PCLCard
{
    public static final PCLCardData DATA = register(RiseKujikawa.class, ConjurerResources.conjurer)
            .setPower(1, CardRarity.RARE)
            .setAffinities(PCLAffinity.Star)
            .setTags(
                    PCLCardTag.Delayed.make(1, array(0, 1)),
                    PCLCardTag.Innate.make(0, array(1, 0)),
                    PCLCardTag.Loyal.make(0, array(1, 0))
            )
            .setMultiformData(2)
            .setLoadout(ConjurerPlayerData.persona);

    public RiseKujikawa()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(new PCond_Redox(), PMultiSkill.join(new PMove_GainReaction(3).setUpgrade(0, 1), PMove.gainTempHP(1).setUpgrade(0, 1))));
        addGainPower(PTrigger.interactable(getSpecialMove(0, this::specialMove)));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.add(new ShuffleOrbs(1));
    }
}
