package pinacolada.cards.conjurer.basic;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.effects.vfx.ScreenFreezingEffect;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.FrostbitePower;
import pinacolada.powers.conjurer.GelusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class SheerCold extends PCLCard
{
    public static final PCLCardData DATA = register(SheerCold.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Blue)
            .setCore();

    public SheerCold()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.playVFX(new ScreenFreezingEffect());
        PCLActions.bottom.applyPower(new SheerColdPower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 2).setUpgrade(1);
    }

    public static class SheerColdPower extends PSpecialCardPower
    {
        public SheerColdPower(AbstractCreature owner, PSkill move)
        {
            super(SheerCold.DATA, owner, move);
        }

        @Override
        public void onInitialApplication()
        {
            super.onInitialApplication();

            PCLActions.bottom.callback(() -> {
                ConjurerReactionMeter.meter.getElementButton(PCLAffinity.Blue).addAdditionalPower(FrostbitePower.POWER_ID);
            });
        }

        public void atEndOfTurn(boolean isPlayer)
        {
            super.atEndOfTurn(isPlayer);
            for (AbstractMonster mo : GameUtilities.getEnemies(true))
            {
                GelusPower po = GameUtilities.getPower(mo, GelusPower.class);
                if (po != null)
                {
                    PCLActions.bottom.applyPower(mo, mo, new FrostbitePower(mo, po.amount * move.amount));
                }
            }
        }
    }
}
