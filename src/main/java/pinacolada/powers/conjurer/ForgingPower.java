package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import extendedui.EUI;
import extendedui.EUIInputManager;
import extendedui.EUIUtils;
import extendedui.utilities.EUIColors;
import pinacolada.actions.PCLActions;
import pinacolada.annotations.VisiblePower;
import pinacolada.cards.base.PCLCard;
import pinacolada.dungeon.CombatManager;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.PCLSFX;
import pinacolada.powers.PCLPower;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;
import pinacolada.utilities.PCLRenderHelpers;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

@VisiblePower
public class ForgingPower extends PCLPower {
    public static final PCLPowerData DATA = register(ForgingPower.class, ConjurerResources.conjurer)
            .setType(PowerType.BUFF)
            .setEndTurnBehavior(PCLPowerData.Behavior.Permanent)
            .setTooltip(ConjurerResources.conjurer.tooltips.forging);
    public static final int PER_STACK = 5;
    public static AbstractCard targetCard;
    private float pulse;
    private boolean busy;

    public ForgingPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
    }

    @Override
    public String getUpdatedDescription() {
        return formatDescription(0, PER_STACK);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.WIND, 0.95f, 1.05f);
    }

    @Override
    protected void renderIconsImpl(SpriteBatch sb, float x, float y, Color borderColor, Color imageColor) {
        super.renderIconsImpl(sb, x, y, borderColor, imageColor);
        if (amount >= PER_STACK) {
            PCLRenderHelpers.drawGlowing(sb, s -> PCLRenderHelpers.drawCentered(sb, EUIColors.white(pulse), this.img, x, y, 32.0F, 32.0F, 1f + pulse * 0.4f, 0.0F));
        }
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, String.valueOf(amount), x, y, fontScale, amount >= PER_STACK ? EUIColors.green(c.a) : EUIColors.white(c.a) );
    }

    public void update(int slot) {
        super.update(slot);
        if (amount >= PER_STACK && player != null && player.hoveredCard != null && GameUtilities.canAcceptInput()) {
            AbstractCard c = player.hoveredCard;
            if (pulse <= 0f) {
                pulse = 1f;
            }
            else {
                pulse -= EUI.delta();
            }
            if (EUIInputManager.rightClick.isJustPressed() && !busy) {
                busy = true;
                flash();
                PCLCard pC = EUIUtils.safeCast(c, PCLCard.class);
                if (pC != null && pC.maxUpgrades() > 0 && pC.timesUpgraded >= pC.maxUpgrades()) {
                    pC.upgradeLevelIncrease += 1;
                }
                PCLActions.bottom.callback(new UpgradeSpecificCardAction(c), __ -> {
                    PCLEffects.TopLevelQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2f, Settings.HEIGHT / 2f));
                    c.calculateCardDamage(null);
                    busy = false;
                    targetCard = c;
                    CombatManager.onSpecificPowerActivated(this, owner, false);
                    reducePower(PER_STACK);
                });
            }
        }
        else {
            pulse = 0;
        }
    }
}
