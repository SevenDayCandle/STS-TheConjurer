package pinacolada.cards.conjurer.series.shinmegamitensei;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class BlackFrost extends PCLCard
{
    public static final PCLCardData DATA = register(BlackFrost.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Magical, PCLCardTarget.RandomEnemy)
            .setDamage(5, 1)
            .setPriority(1)
            .setHp(3, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public BlackFrost()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AbstractGameAction.AttackEffect.FIRE);
        addUseMove(PCond.cooldown(0), PMove.applyToRandom(2, PCLElementHelper.Ignis, PCLElementHelper.Gelus));
    }
}
