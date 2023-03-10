package pinacolada.cards.conjurer.basic;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Thermogenesis extends PCLCard
{
    public static final PCLCardData DATA = register(Thermogenesis.class, ConjurerResources.conjurer)
            .setAttack(1,CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(6, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setCore();

    public Thermogenesis()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addUseMove(PCond.onDraw(), PMove.applyToRandom(2, PCLElementHelper.Ignis));
    }
}
