package pinacolada.relics.conjurer;

import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import pinacolada.actions.PCLActions;
import pinacolada.actions.special.CreateRandomCurses;
import pinacolada.interfaces.subscribers.OnChannelOrbSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.relics.PCLRelic;
import pinacolada.utilities.GameUtilities;

public class SoulGem3 extends PCLRelic implements OnChannelOrbSubscriber
{
    public static final String ID = createFullID(SoulGem3.class);
    public static final int POWER_AMOUNT = 3;
    public static final int TRIGGER_THRESHOLD = 3;

    public SoulGem3()
    {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void onChannelOrb(AbstractOrb orb)
    {
        addCounter(1);
        if (counter > TRIGGER_THRESHOLD)
        {
            PCLActions.bottom.add(new CreateRandomCurses(1, player.drawPile));
            setCounter(0);
        }
        flash();
    }

    @Override
    public void atBattleStart()
    {
        CombatManager.onChannelOrb.subscribe(this);
        setCounter(0);
    }

    @Override
    public void atTurnStartPostDraw()
    {
        super.atTurnStartPostDraw();
        int oldAmount = GameUtilities.getPowerAmount(player, FocusPower.POWER_ID);
        if (oldAmount < POWER_AMOUNT)
        {
            PCLActions.bottom.gain(PCLPowerHelper.Focus, POWER_AMOUNT - oldAmount);
        }
    }
}