package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.effects.vfx.ScreenOnFireEffect3;
import pinacolada.misc.PCLUseInfo;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class BlazingHeat extends PCLCard
{
    public static final PCLCardData DATA = register(BlazingHeat.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public BlazingHeat()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.playVFX(new ScreenOnFireEffect3());
        PCLActions.bottom.applyPower(new BlazingHeatPower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 5).setUpgrade(2);
    }

    public static class BlazingHeatPower extends PSpecialCardPower
    {

        public BlazingHeatPower(AbstractCreature owner, PSkill move)
        {
            super(BlazingHeat.DATA, owner, move);
        }

        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            for (AbstractMonster mo : GameUtilities.getEnemies(true))
            {
                IgnisPower po = GameUtilities.getPower(mo, IgnisPower.class);
                if (po != null)
                {
                    PCLActions.bottom.dealDamage(owner, mo, po.amount * move.amount, DamageInfo.DamageType.HP_LOSS, PCLAttackVFX.BURN);
                }
            }
        }
    }
}
