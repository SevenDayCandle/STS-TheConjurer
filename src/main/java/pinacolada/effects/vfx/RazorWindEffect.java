package pinacolada.effects.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
import extendedui.EUIRenderHelpers;
import extendedui.EUIUtils;
import extendedui.ui.TextureCache;
import pinacolada.effects.PCLEffects;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.utilities.PCLRenderHelpers;

public class RazorWindEffect extends VisualEffect {
    protected static final Color PARTICLE_COLOR = Color.LIME.cpy();
    public static final TextureCache image = ConjurerImages.Effects.airSlice;
    protected float x;
    protected float y;
    protected float targetY;
    protected float vfxTimer;
    protected float vfxFrequency;

    public RazorWindEffect(float x, float y, float targetY, float vX, float aX) {
        super(1, x, y, random(5f, 10f), 0.75f);
        this.duration = 1;
        this.x = x;
        this.y = y;
        this.targetY = targetY;
        this.vX = vX * Settings.scale;
        this.aX = vX * Settings.scale;
        this.vRot = random(1000f, 1200f);
        this.color = Color.WHITE.cpy();
        this.vfxFrequency = 0.01f;
    }

    public void render(SpriteBatch sb) {
        renderImage(sb, image.texture(), x, y, false, false);
    }

    public RazorWindEffect setFrequency(float frequency) {
        this.vfxFrequency = MathUtils.clamp(frequency, 0.01f, startingDuration / 5f);

        return this;
    }

    @Override
    protected void updateInternal(float deltaTime) {
        x += vX * deltaTime;
        y = Interpolation.pow2OutInverse.apply(y, targetY, Math.min(1f, (1f - duration) / 2f));
        vX += aX * deltaTime;
        rotation += vRot * deltaTime;

        if ((1f - duration) < 0.1f) {
            color.a = Interpolation.fade.apply(0.1f, 1f, (1f - duration) * 7f);
        }
        else {
            color.a = Interpolation.pow2Out.apply(0.1f, 1f, duration);
        }

        vfxTimer -= deltaTime;
        if (vfxTimer < 0f) {
            PCLEffects.Queue.particle(EUIUtils.random(TornadoEffect.IMAGES_AIR).texture(), x, y + (random(-100, 100) * Settings.scale))
                    .setScale(random(0.04f, 0.6f))
                    .setColor(new Color(MathUtils.random(0.8f, 1f), 1f, MathUtils.random(0.8f, 1f), 1))
                    .setBlendingMode(EUIRenderHelpers.BlendingMode.Glowing)
                    .setOpacity(random(0.3F, 1.0F))
                    .setSpeed(random(-300f, -50f) * Math.signum(vX), random(-200f, 200f))
                    .setRotation(random(-10f, 10f), randomBoolean() ? random(-800f, -500f) : random(500f, 800f));
            PCLEffects.Queue.particle(image.texture(), x, y)
                    .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                    .setOpacity(0.5f)
                    .setColor(new Color(MathUtils.random(0.8f, 1f), 1f, MathUtils.random(0.8f, 1f), 0.5f))
                    .setRotation(rotation)
                    .setScale(scale).setRotation(0, random(300f, 400f));
            PCLEffects.Queue.add(new LightFlareParticleEffect(this.x, this.y, PARTICLE_COLOR));
            vfxTimer = vfxFrequency;
        }

        super.updateInternal(deltaTime);
    }
}
