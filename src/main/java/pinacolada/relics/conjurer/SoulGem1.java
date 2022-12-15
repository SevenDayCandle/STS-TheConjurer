package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import pinacolada.actions.PCLActions;
import pinacolada.actions.special.CreateRandomCurses;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.utilities.GameUtilities;

public class SoulGem1 extends PCLRelic
{
    public static final String ID = createFullID(SoulGem1.class);
    public static final int POWER_AMOUNT = 3;
    public static final int TRIGGER_THRESHOLD = 6;

    public SoulGem1()
    {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        if (c.type == AbstractCard.CardType.ATTACK)
        {
            addCounter(1);
            if (counter > TRIGGER_THRESHOLD)
            {
                PCLActions.bottom.add(new CreateRandomCurses(1, player.drawPile));
                setCounter(0);
            }
            flash();
        }
    }

    @Override
    public void atBattleStart()
    {
        setCounter(0);
    }

    @Override
    public void atTurnStartPostDraw()
    {
        super.atTurnStartPostDraw();
        int oldAmount = GameUtilities.getPowerAmount(player, StrengthPower.POWER_ID);
        if (oldAmount < POWER_AMOUNT)
        {
            PCLActions.bottom.gain(PCLPowerHelper.Strength, POWER_AMOUNT - oldAmount);
        }
    }
}