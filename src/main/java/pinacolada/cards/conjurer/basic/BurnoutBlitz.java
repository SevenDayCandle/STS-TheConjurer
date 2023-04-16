package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.conjurer.status.Status_SearingBurn;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class BurnoutBlitz extends PCLCard
{
    public static final PCLCardData DATA = register(BurnoutBlitz.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(13, 2, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public BurnoutBlitz()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(EffekseerEFK.FIRE08);
        addUseMove(PMove.createDiscardPile(2, Status_SearingBurn.DATA.ID));
    }
}
