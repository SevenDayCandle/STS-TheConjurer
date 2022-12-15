package pinacolada.cards.conjurer.series.touhouproject;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PTrigger;
import pinacolada.skills.conjurer.conditions.PCond_PayReaction;
import pinacolada.skills.skills.base.conditions.PCond_EvokeOrb;
import pinacolada.skills.skills.special.moves.PMove_StealTempHP;

public class FlandreScarlet_RemiliaScarlet extends PCLCard
{
    public static final PCLCardData DATA = register(FlandreScarlet_RemiliaScarlet.class, ConjurerResources.conjurer)
            .setPower(2, CardRarity.SPECIAL)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue, PCLAffinity.Dark)
            .setRTags(PCLCardTag.Ethereal)
            .setLoadout(ConjurerPlayerData.touhouProject);

    public FlandreScarlet_RemiliaScarlet()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addGainPower(PTrigger.when(new PCond_EvokeOrb(), new PMove_StealTempHP(2, AbstractGameAction.AttackEffect.NONE, PCLCardTarget.RandomEnemy)));
        addGainPower(PTrigger.interactable(new PCond_PayReaction(10), new PMove_StealTempHP(6, AbstractGameAction.AttackEffect.NONE, PCLCardTarget.RandomEnemy)));
    }

}
