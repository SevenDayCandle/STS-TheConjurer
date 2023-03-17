package pinacolada.cards.conjurer.basic;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class SolarFlare extends PCLCard
{
    public static final PCLCardData DATA = register(SolarFlare.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE)
            .setDamage(8, 1)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public SolarFlare()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addUseMove(CMod.perReaction(7).setUpgrade(-1), PMove.modifyDamage(1));
    }
}
