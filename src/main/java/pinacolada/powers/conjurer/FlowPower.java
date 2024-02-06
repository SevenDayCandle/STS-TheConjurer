package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import extendedui.utilities.EUIColors;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.dungeon.CombatManager;
import pinacolada.dungeon.DrawPileCardPreview;
import pinacolada.effects.PCLSFX;
import pinacolada.interfaces.providers.DrawPileCardPreviewProvider;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

@VisiblePower
public class FlowPower extends PCLPower implements DrawPileCardPreviewProvider {
    public static final PCLPowerData DATA = register(FlowPower.class, ConjurerResources.conjurer)
            .setType(PowerType.BUFF)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent)
            .setTooltip(ConjurerResources.conjurer.tooltips.flow);
    public static final int PER_STACK = 5;
    public static AbstractCard drawn;
    private DrawPileCardPreview preview;

    public FlowPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public AbstractCard findCard() {
        return amount >= PER_STACK ? AbstractDungeon.player.drawPile.getTopCard() : null;
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, PER_STACK);
    }

    @Override
    public void onClick(AbstractCard highlighted) {
        if (amount >= PER_STACK) {
            amount -= PER_STACK;
            flash();
            PCLActions.bottom.draw(highlighted).addCallback(c -> {
                drawn = c;
                CombatManager.onSpecificPowerActivated(this, owner, false);
                this.refreshCard();
            });
        }
    }

    @Override
    public void onDrawOrDiscard() {
        super.onDrawOrDiscard();
        refreshCard();
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

        preview = subscribe();
    }

    @Override
    public void onRemove() {
        super.onRemove();

        unsubscribe();
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.WIND, 0.95f, 1.05f);
    }

    @Override
    protected void renderIconsImpl(SpriteBatch sb, float x, float y, Color borderColor, Color imageColor) {
        super.renderIconsImpl(sb, x, y, borderColor, imageColor);
        if (preview != null && preview.isHighlighted()) {
            PCLRenderHelpers.drawGlowing(sb, s -> PCLRenderHelpers.drawCentered(s, imageColor, this.img, x, y, 32.0F, 32.0F, 1f, 0.0F));
        }
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, String.valueOf(amount), x, y, fontScale, amount >= PER_STACK ? EUIColors.green(c.a) : EUIColors.white(c.a) );
    }

    @Override
    public void stackPower(int amount, boolean updateBase) {
        super.stackPower(amount, updateBase);

        refreshCard();
    }
}
