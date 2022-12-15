package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import pinacolada.actions.PCLActions;
import pinacolada.actions.special.CreateRandomCurses;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.utilities.GameUtilities;

public class SoulGem2 extends PCLRelic
{
    public static final String ID = createFullID(SoulGem2.class);
    public static final int POWER_AMOUNT = 3;
    public static final int TRIGGER_THRESHOLD = 5;

    public SoulGem2()
    {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m)
    {
        if (c.baseBlock > 0)
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
        int oldAmount = GameUtilities.getPowerAmount(player, DexterityPower.POWER_ID);
        if (oldAmount < POWER_AMOUNT)
        {
            PCLActions.bottom.gain(PCLPowerHelper.Dexterity, POWER_AMOUNT - oldAmount);
        }
    }
}