package pinacolada.cards.conjurer.basic;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLUseInfo;
import pinacolada.effects.vfx.ScreenFreezingEffect;
import pinacolada.effects.vfx.SnowballEffect;
import pinacolada.powers.PSpecialCardPower;
import pinacolada.powers.conjurer.ChilledPower;
import pinacolada.powers.conjurer.FrostbitePower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PSkill;
import pinacolada.skills.skills.PSpecialSkill;
import pinacolada.utilities.GameUtilities;

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
        addSpecialMove(0, this::action, 1).setUpgrade(1);
    }

    public static class SheerColdPower extends PSpecialCardPower
    {

        public SheerColdPower(AbstractCreature owner, PSkill move)
        {
            super(SheerCold.DATA, owner, move);
        }

        @Override
        public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source)
        {
            super.onApplyPower(power, target, source);

            if (target != owner && power.ID.equals(ChilledPower.POWER_ID) && GameUtilities.getPowerAmount(target, ChilledPower.POWER_ID) >= move.amount)
            {
                PCLActions.top.playVFX(new SnowballEffect(owner.hb.cX, owner.hb.cY, target.hb.cX, target.hb.cY)
                        .setColor(Color.NAVY, Color.NAVY).setRealtime(true));
                PCLActions.bottom.applyPower(source, new FrostbitePower(target, move.extra));
            }
        }
    }
}
