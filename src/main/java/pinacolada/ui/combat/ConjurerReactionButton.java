package pinacolada.ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.ui.controls.EUIImage;
import extendedui.ui.hitboxes.EUIHitbox;
import extendedui.utilities.EUIColors;
import pinacolada.actions.PCLActions;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.affinity.GenericFlashEffect;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

public class ConjurerReactionButton extends EUIImage
{
    public static final float ICON_SIZE = scale(32);
    public static final Color HIGHLIGHT_AMPLIFY_COLOR = new Color(0.4f, 0.7f, 1f, 1f);
    public static final Color HIGHLIGHT_INTENSIFY_COLOR = new Color(0.7f, 1f, 0.4f, 1f);
    public static float PROGRESS_PERCENT = 0.65f;
    public static float ROTATION_PERCENT = 0.03406598f;
    public static float ROTATION_MOD = -0.6883351f;
    public final ConjurerElementButton source;
    public final ConjurerElementButton target;
    protected final EUIImage overlay;
    public ConjurerElementButton.Type type;
    public ConjurerElementButton.Type baseType;

    public ConjurerReactionButton(ConjurerElementButton target, ConjurerElementButton source, ConjurerElementButton.Type type)
    {
        super(PGR.core.images.core.rightArrow.texture(), new EUIHitbox(0, 0, ICON_SIZE, ICON_SIZE));
        this.target = target;
        this.source = source;
        this.baseType = this.type = type;
        this.overlay = new EUIImage(this.texture, this.hb).setColor(EUIColors.black(0)).setBlendingMode(EUIRenderHelpers.BlendingMode.Glowing);
        setColorForType();
        rotation = EUIRenderHelpers.getAngleDegrees(this.source.hb.cX, this.source.hb.cY, this.target.hb.cX, this.target.hb.cY);
        overlay.rotation = rotation;

        setTooltip(target.tooltip.title, "");
    }

    public void highlight()
    {
        overlay.setTargetColor(type == ConjurerElementButton.Type.Combust ? HIGHLIGHT_AMPLIFY_COLOR : HIGHLIGHT_INTENSIFY_COLOR);
    }

    public void unhighlight()
    {
        overlay.setTargetColor(EUIColors.black(0));
    }

    public void updateImpl()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.J))
        {
            PROGRESS_PERCENT += Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 0.001f : -0.001f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K))
        {
            ROTATION_PERCENT += Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 0.001f : -0.001f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L))
        {
            ROTATION_MOD += Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 0.001f * Math.sqrt(2) : -0.001f * Math.sqrt(2);
        }
        this.hb.move(MathUtils.lerp(source.hb.cX, target.hb.cX, PROGRESS_PERCENT + MathUtils.sin(rotation + ROTATION_MOD) * ROTATION_PERCENT), MathUtils.lerp(source.hb.cY, target.hb.cY, 1 - PROGRESS_PERCENT + MathUtils.cos(rotation + ROTATION_MOD) * ROTATION_PERCENT));
        super.updateImpl();
        if (hb.hovered)
        {
            boolean morph = source.meter.getMorphCount() > 0;
            if (morph)
            {
                highlight();
            }
            updateDescription(morph);
            if (InputHelper.justClickedLeft)
            {
                PCLActions.bottom.callback(() -> {
                    if (source.meter.tryUseMorph(this)) {
                        PCLEffects.Queue.add(new GenericFlashEffect(this.texture, this.hb.x, this.hb.y, true).setScale(Settings.scale * 0.5f));
                    }
                });
            }
        }

        overlay.updateImpl();
    }

    public void renderImpl(SpriteBatch sb)
    {
        super.renderImpl(sb);
        overlay.renderImpl(sb);
    }

    public void renderCentered(SpriteBatch sb)
    {
        super.renderCentered(sb);
        overlay.renderCentered(sb);
    }

    public void updateDescription(boolean morph)
    {
        if (PGR.isLoaded())
        {
            tooltip.setIcon(target.elementPower().tooltip.icon);
            tooltip.setDescription(type == ConjurerElementButton.Type.Combust ?
                    EUIUtils.format(PGR.core.strings.combat.conjurerMeterCombust, source.affinity.getTooltip(), target.elementPower().tooltip, PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(source.affinity))) :
                    EUIUtils.format(PGR.core.strings.combat.conjurerMeterRedox, source.affinity.getTooltip(), target.elementPower().tooltip, PCLRenderHelpers.decimalFormat(AbstractPCLElementalPower.getAmplifyMultiplier(source.affinity))));
            if (source.meter.getMorphCount() > 0)
            {
                tooltip.setDescription(tooltip.description() + " | " + EUIUtils.format(PGR.core.strings.combat.conjurerMeterSwitch, type == ConjurerElementButton.Type.Combust ? ConjurerResources.conjurer.tooltips.redox.title : ConjurerResources.conjurer.tooltips.combust.title));
            }
        }
    }

    public void reset()
    {
        type = baseType;
        setColorForType();
    }

    public void morphAction()
    {
        morph();
        ConjurerReactionButton button = source.reactions.get(target.affinity);
        if (button != null)
        {
            button.morph();
        }
    }

    public void morph()
    {
        type = type == ConjurerElementButton.Type.Combust ? ConjurerElementButton.Type.Redox : ConjurerElementButton.Type.Combust;
        setColorForType();
    }

    private void setColorForType()
    {
        this.setColor(type == ConjurerElementButton.Type.Combust ? EUIColors.blue(1) : Color.TAN);
        this.color.a = 0.6f;
    }

}
