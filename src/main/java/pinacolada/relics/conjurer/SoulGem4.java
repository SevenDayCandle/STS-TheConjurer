package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pinacolada.actions.PCLActions;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.common.ResistancePower;
import pinacolada.relics.PCLRelic;
import pinacolada.utilities.GameUtilities;

public class SoulGem4 extends PCLRelic
{
    public static final String ID = createFullID(SoulGem4.class);
    public static final int POWER_AMOUNT = 3;
    public static final int TRIGGER_THRESHOLD = 16;

    public SoulGem4()
    {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void update()
    {
        super.update();
        if (counter < 0)
        {
            this.usedUp();
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        if (counter >= 0)
        {
            addCounter(1);
            flash();
            if (counter >= TRIGGER_THRESHOLD)
            {
                setCounter(-1);
            }
        }
    }

    @Override
    public void atBattleStart()
    {
        setCounter(0);
    }

    @Override
    public void atTurnStart()
    {
        super.atTurnStart();
        if (counter >= 0)
        {
            setCounter(0);
        }
    }

    @Override
    public void atTurnStartPostDraw()
    {
        super.atTurnStartPostDraw();
        if (counter >= 0)
        {
            int oldAmount = GameUtilities.getPowerAmount(player, ResistancePower.POWER_ID);
            if (oldAmount < POWER_AMOUNT)
            {
                PCLActions.bottom.gain(PCLPowerHelper.Resistance, POWER_AMOUNT - oldAmount);
            }
        }
    }
}