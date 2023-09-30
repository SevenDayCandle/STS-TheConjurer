package pinacolada.powers.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import pinacolada.interfaces.subscribers.OnTryReducePowerSubscriber;
import pinacolada.powers.PCLPowerData;
import pinacolada.powers.PCLSubscribingPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

public class StabilizingPower extends PCLSubscribingPower implements OnTryReducePowerSubscriber {
    public static final PCLPowerData DATA = new PCLPowerData(StabilizingPower.class, ConjurerResources.conjurer)
            .setEndTurnBehavior(PCLPowerData.Behavior.Special);
    protected AbstractPower originalPower;

    public StabilizingPower(AbstractPower originalPower, AbstractCreature owner, AbstractCreature source, int amount) {
        super(DATA, owner, source, amount);
        this.originalPower = originalPower;
        this.img = originalPower.img;
        this.region128 = originalPower.region128;
        mainTip.icon = this.region128 != null ? this.region128 : img != null ? new TextureRegion(img) : null;
        updateDescription();
    }

    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        reducePower(1);
    }

    @Override
    public String getUpdatedDescription() {
        if (originalPower == null) {
            return formatDescription(0, PGR.core.tooltips.power.title, amount);
        }
        return formatDescription(0, originalPower.name, amount);
    }

    @Override
    protected void renderIconsImpl(SpriteBatch sb, float x, float y, Color borderColor, Color imageColor) {
        PCLRenderHelpers.drawGrayscale(sb, (s) ->
                super.renderIconsImpl(s, x, y, borderColor, imageColor)
        );
    }

    @Override
    public boolean tryReducePower(AbstractPower power, AbstractCreature target, AbstractCreature source, AbstractGameAction action) {
        return power == null || !(originalPower.ID.equals(power.ID) && target == owner);
    }
}