package pinacolada.cards.conjurer.basic;


import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PSpecialSkill;

@VisibleCard
public class Melt extends PCLCard
{
    public static final PCLCardData DATA = register(Melt.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Magical)
            .setDamage(7, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public Melt()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.NONE).setDamageEffect(PCLEffekseerEFX.FIRE07);
        addUseMove(PCond.ifElse(
                getSpecialMove(0, this::specialMove, 25).setUpgrade(8),
                PMove.applyToSingle(1, PCLElementHelper.Ignis),
                CCond.combust()));
    }

    public void specialMove(PSpecialSkill move, PCLUseInfo info)
    {
        if (info.target != null)
        {
            int reduction = MathUtils.ceil(info.target.currentHealth * move.amount / 100f);
            PCLActions.bottom.loseHP(info.source, info.target, reduction, AbstractGameAction.AttackEffect.FIRE);
        }
    }
}
