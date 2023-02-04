package pinacolada.cards.conjurer.basic;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class HeatAcceleration extends PCLCard
{
    public static final PCLCardData DATA = register(HeatAcceleration.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE)
            .setDamage(4, 1, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public HeatAcceleration()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addUseMove(PMove.gain(2, PCLPowerHelper.NextTurnDraw).setUpgrade(1));
        addUseMove(PMove.triggerAlly(PCLCardTarget.RandomAlly, 1));
    }
}
