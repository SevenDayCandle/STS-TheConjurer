package pinacolada.cards.conjurer.basic;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.utilities.ColoredString;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.ui.combat.ConjurerReactionMeter;

public class VerdantGale extends PCLCard
{
    public static final PCLCardData DATA = register(VerdantGale.class, ConjurerResources.conjurer)
            .setPower(3, CardRarity.SPECIAL)
            .setAffinities(2, PCLAffinity.Green)
            .setUTags(PCLCardTag.Retain)
            .setColorless();

    public VerdantGale()
    {
        super(DATA);
    }

    public void action(PSpecialSkill move, PCLUseInfo info)
    {
        PCLActions.bottom.applyPower(new VerdantGalePower(info.source, move));
    }

    public void setup(Object input)
    {
        addSpecialMove(0, this::action, 8, 1).setUpgrade(-2);
    }

    public static class VerdantGalePower extends PSpecialCardPower
    {
        protected int count;

        public VerdantGalePower(AbstractCreature owner, PSkill move)
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
                PCLActions.bottom.draw(move.extra);
                flash();
            }
        }

        @Override
        protected ColoredString getSecondaryAmount(Color c)
        {
            return new ColoredString(count, Color.GREEN, c.a);
        }
    }
}
