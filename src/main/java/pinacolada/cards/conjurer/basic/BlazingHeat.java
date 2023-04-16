package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.vfx.ScreenOnFireEffect3;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
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

    public void setup(Object input)
    {
        addSpecialPower(0, (s, i) -> new BlazingHeatPower(i.source, s), 1).setUpgrade(1);
    }

    public static class BlazingHeatPower extends PSpecialCardPower
    {
        public BlazingHeatPower(AbstractCreature owner, PSkill<?> move)
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
                    PCLActions.bottom.applyPower(owner, PCLCardTarget.Single, PCLElementHelper.Blasted, po.amount);
                }
            }
        }

        public void onInitialApplication()
        {
            super.onInitialApplication();
            PCLActions.bottom.playVFX(new ScreenOnFireEffect3());
            PCLActions.bottom.callback(() -> {
                for (AbstractPower po : GameUtilities.getPowers(BlastedPower.POWER_ID))
                {
                    if (po instanceof BlastedPower)
                    {
                        ((BlastedPower) po).expanded = true;
                    }
                }
            });
        }
    }
}
