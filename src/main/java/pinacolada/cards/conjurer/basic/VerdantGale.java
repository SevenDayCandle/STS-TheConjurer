package pinacolada.cards.conjurer.basic;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import extendedui.utilities.ColoredString;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.ui.combat.ConjurerReactionMeter;
import pinacolada.utilities.GameUtilities;

@VisibleCard
public class VerdantGale extends PCLCard
{
    public static final PCLCardData DATA = register(VerdantGale.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.RARE)
            .setAffinities(2, PCLAffinity.Green)
            .setUTags(PCLCardTag.Retain)
            .setCore();

    public VerdantGale()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addSpecialPower(0, (s, i) -> new VerdantGalePower(i.source, s), 9, 4).setUpgradeExtra(2);
    }

    public static class VerdantGalePower extends PSpecialCardPower
    {
        protected int count;

        public VerdantGalePower(AbstractCreature owner, PSkill<?> move)
        {
            super(VerdantGale.DATA, owner, move);
        }

        @Override
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
        {
            super.onApplyPower(power, target, source);

            if (ConjurerReactionMeter.meter.isPowerElemental(power.ID, PCLAffinity.Green))
            {
                count += power.amount;
            }

            while (count >= move.amount)
            {
                count -= move.amount;
                doAction();
                flash();
            }
        }

        public void doAction()
        {
            PCLActions.bottom.gain(PCLPowerHelper.NextTurnDraw, 1);
            AbstractCreature target = EUIUtils.findMin(GameUtilities.getEnemies(true), mo -> mo.currentHealth);
            if (target != null)
            {
                PCLActions.bottom.applyPower(target, PCLCardTarget.Single, PCLPowerHelper.Poison, move.extra);
            }
        }

        @Override
        protected ColoredString getSecondaryAmount(Color c)
        {
            return new ColoredString(count, Color.GREEN, c.a);
        }
    }
}
