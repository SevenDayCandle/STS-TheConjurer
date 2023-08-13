package pinacolada.effects.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import extendedui.EUIRenderHelpers;
import extendedui.ui.TextureCache;
import extendedui.utilities.EUIColors;
import pinacolada.effects.PCLEffects;
import pinacolada.resources.conjurer.ConjurerImages;

import java.util.ArrayList;

public class StarEffect extends VisualEffect {
    public static final TextureCache[] IMAGES = {ConjurerImages.Effects.sparkle1, ConjurerImages.Effects.sparkle2, ConjurerImages.Effects.sparkle3, ConjurerImages.Effects.sparkle4};
    protected static final ArrayList<Float> RGB = new ArrayList<>(3);

    protected float vfxFrequency = 0.015f;
    protected float vfxTimer;

    public StarEffect(float x, float y, float horizontalSpeed, float verticalSpeed) {
        this(x, y, horizontalSpeed, verticalSpeed, random(-600f, 600f), random(0.5f, 3.0f));
    }

    public StarEffect(float x, float y, float horizontalSpeed, float verticalSpeed, float rotationSpeed, float scale) {
        super(random(0.5f, 1f), x, y, random(-10f, 10f), scale);

        this.vX = horizontalSpeed;
        this.vY = verticalSpeed;
        this.vRot = rotationSpeed;

        if (randomBoolean()) {
            this.rotation *= -1;
        }

        setRandomColor();
    }

    public void render(SpriteBatch sb) {
        renderImage(sb, ConjurerImages.Effects.star.texture(), x, y, false, false);
    }

    @Override
    protected void updateInternal(float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;
        rotation += vRot * deltaTime;

        if (scale > 0.3f) {
            scale -= deltaTime * 2f;
        }

        if ((1f - duration) < 0.1f) {
            color.a = Interpolation.fade.apply(0.1f, 1f, (1f - duration) * 10f);
        }
        else {
            color.a = Interpolation.pow2Out.apply(0.1f, 1f, duration);
        }

        vfxTimer -= deltaTime;
        if (vfxTimer < 0f) {
            PCLEffects.Queue.particle(randomTexture(IMAGES), x, y)
                    .setBlendingMode(EUIRenderHelpers.BlendingMode.Glowing)
                    .setFlip(randomBoolean(), false)
                    .setRotation(random(-10f, 10f), random(-12f, 12f))
                    .setSpeed(random(-120f, -30f) * Math.signum(vX), random(-60f, 60f))
                    .setScale(random(0.01f, 0.28f) * Math.min(1f, this.scale))
                    .setColor(EUIColors.random(0.83f, 1f, false)).renderBehind(true);
            if (randomBoolean(0.72f) && this.scale >= 0.7f) {
                PCLEffects.Queue.add(new StarEffect(x, y, vX * -0.25f, random(-vX * 0.05f, vX * 0.05f), random(-1000f, 1000f), random(0.05f, Math.min(0.5f, this.scale))));
            }
            vfxTimer = vfxFrequency;
        }

        super.updateInternal(deltaTime);
    }

    public StarEffect setFrequency(float frequency) {
        this.vfxFrequency = MathUtils.clamp(frequency, 0.01f, startingDuration / 5f);

        return this;
    }

    public StarEffect setRandomColor() {
        RGB.clear();
        RGB.add(0.48f);
        RGB.add(1f);
        RGB.add(random(0.48f, 1f));

        this.color = new Color(RGB.remove(random(0, 2)), RGB.remove(random(0, 1)), RGB.remove(0), 0.15f);

        return this;
    }
}
