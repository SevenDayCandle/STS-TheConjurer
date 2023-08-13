package pinacolada.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUIImage;
import extendedui.ui.hitboxes.EUIHitbox;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import extendedui.utilities.EUIColors;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.utilities.PCLRenderHelpers;

public class ConjurerReactionButton extends EUIImage {
    public static final float ICON_SIZE = scale(32);
    public static final Color HIGHLIGHT_AMPLIFY_COLOR = new Color(0.4f, 0.7f, 1f, 1f);
    public static float PROGRESS_PERCENT = 0.65f;
    public static float ROTATION_PERCENT = 0.03406598f;
    public static float ROTATION_MOD = -0.6883351f;
    protected final EUIImage overlay;
    public final ConjurerElementButton source;
    public final ConjurerElementButton target;
    protected EUIKeywordTooltip keyword;

    public ConjurerReactionButton(ConjurerElementButton target, ConjurerElementButton source) {
        super(PCLCoreImages.Core.rightArrow.texture(), new EUIHitbox(0, 0, ICON_SIZE, ICON_SIZE));
        this.target = target;
        this.source = source;
        this.overlay = new EUIImage(this.texture, this.hb).setColor(EUIColors.black(0)).setBlendingMode(EUIRenderHelpers.BlendingMode.Glowing);
        this.setColor(EUIColors.blue(0.6f));
        rotation = EUIRenderHelpers.getAngleDegrees(this.source.hb.cX, this.source.hb.cY, this.target.hb.cX, this.target.hb.cY);
        overlay.rotation = rotation;

        keyword = new EUIKeywordTooltip(target.tooltip.title);
        setTooltip(keyword);
    }

    public boolean hasReverse() {
        return ConjurerReactionMeter.meter.getReactionButton(source.affinity, target.affinity) != null;
    }

    public void highlight() {
        overlay.setTargetColor(HIGHLIGHT_AMPLIFY_COLOR);
    }

    public void renderCentered(SpriteBatch sb) {
        super.renderCentered(sb);
        overlay.renderCentered(sb);
    }

    public void renderImpl(SpriteBatch sb) {
        super.renderImpl(sb);
        overlay.renderImpl(sb);
    }

    public void updateImpl() {
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            PROGRESS_PERCENT += Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 0.001f : -0.001f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            ROTATION_PERCENT += Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 0.001f : -0.001f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            ROTATION_MOD += Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 0.001f * Math.sqrt(2) : -0.001f * Math.sqrt(2);
        }
        if (hasReverse()) {
            this.hb.move(MathUtils.lerp(source.hb.cX, target.hb.cX, PROGRESS_PERCENT + MathUtils.sin(rotation + ROTATION_MOD) * ROTATION_PERCENT), MathUtils.lerp(source.hb.cY, target.hb.cY, 1 - PROGRESS_PERCENT + MathUtils.cos(rotation + ROTATION_MOD) * ROTATION_PERCENT));
        }
        else {
            this.hb.move(MathUtils.lerp(source.hb.cX, target.hb.cX, 0.5f), MathUtils.lerp(source.hb.cY, target.hb.cY, 0.5f));
        }
        super.updateImpl();
        if (hb.hovered) {
            updateDescription();
        }

        overlay.updateImpl();
    }

    public void unhighlight() {
        overlay.setTargetColor(EUIColors.black(0));
    }

    public void updateDescription() {
        if (PGR.isLoaded()) {
            keyword.setIcon(target.elementPower().tooltip.icon);
            keyword.setDescription(
                    EUIUtils.format(ConjurerResources.conjurer.strings.combat_conjurerMeterReact, source.affinity.getTooltip(), target.elementPower().tooltip, PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(source.affinity))));
        }
    }

}
