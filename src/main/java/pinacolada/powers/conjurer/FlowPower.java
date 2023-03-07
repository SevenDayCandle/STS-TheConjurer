package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import extendedui.utilities.ColoredString;
import pinacolada.actions.PCLActions;
import pinacolada.effects.SFX;
import pinacolada.interfaces.listeners.DrawPileCardPreviewProvider;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.ui.combat.DrawPileCardPreview;

public class FlowPower extends PCLPower implements DrawPileCardPreviewProvider
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FlowPower.class);
    public static final int PER_STACK = 5;
    private DrawPileCardPreview preview;

    public FlowPower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        initialize(amount, PowerType.BUFF, false);
    }

    @Override
    public void onInitialApplication()
    {
        super.onInitialApplication();

        preview = subscribe();
    }

    @Override
    public void onRemove()
    {
        super.onRemove();

        unsubscribe();
    }

    @Override
    public void stackPower(int amount, boolean updateBase)
    {
        super.stackPower(amount, updateBase);

        refreshCard();
    }

    @Override
    public AbstractCard findCard()
    {
        return amount >= PER_STACK ? player.drawPile.getTopCard() : null;
    }

    @Override
    public void highlight(DrawPileCardPreview preview)
    {
    }

    @Override
    public void onClick(AbstractCard highlighted)
    {
        if (amount >= PER_STACK)
        {
            amount -= PER_STACK;
            flash();
            PCLActions.bottom.draw(highlighted).addCallback(this::refreshCard);
        }
    }

    @Override
    public void onDrawOrDiscard()
    {
        super.onDrawOrDiscard();
        refreshCard();
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.WIND, 0.95f, 1.05f);
    }

    @Override
    public String getUpdatedDescription()
    {
        return formatDescription(0, PER_STACK);
    }

    @Override
    protected ColoredString getPrimaryAmount(Color c)
    {
        return new ColoredString(amount, amount >= PER_STACK ? Color.GREEN : Color.WHITE, c.a);
    }
}
