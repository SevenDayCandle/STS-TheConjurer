package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.vfx.ScreenOnFireEffect3;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.BurnedPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

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
        addSpecialMove(0, this::action, 3).setUpgrade(1);
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
                BurnedPower po = GameUtilities.getPower(mo, BurnedPower.class);
                if (po != null)
                {
                    PCLActions.bottom.dealDamage(owner, mo, po.amount * move.amount, DamageInfo.DamageType.HP_LOSS, AttackEffects.BURN);
                }
            }
        }
    }
}
