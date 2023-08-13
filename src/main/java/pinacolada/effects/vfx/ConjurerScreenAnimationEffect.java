package pinacolada.effects.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.ui.EUIBase;
import pinacolada.effects.PCLEffect;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.utilities.PCLRenderHelpers;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConjurerScreenAnimationEffect extends PCLEffect {
    public final ConcurrentLinkedQueue<PCLEffect> subEffects = new ConcurrentLinkedQueue<>();
    private final ShapeRenderer renderer;

    public ConjurerScreenAnimationEffect() {
        super(0.14f);
        renderer = new ShapeRenderer();
    }

    protected void firstUpdate() {
        for (int i = 0; i < 16; i++) {
            subEffects.add(makeParticle());
        }
    }

    protected PCLEffect makeParticle() {
        float xRate = MathUtils.random(0f, 1f);
        if (MathUtils.randomBoolean(0.15f)) {
            float yRate = MathUtils.random(0f, 1f);
            return FadingParticleEffect.obtain(ConjurerImages.Effects.bokeh.texture(), xRate * Settings.WIDTH, yRate * Settings.HEIGHT)
                    .setColor(new Color(MathUtils.random(0.3f, 0.6f), MathUtils.random(0.3f, 0.7f), MathUtils.random(0.6f, 0.85f), 0f),
                            new Color(MathUtils.random(0.3f, 0.5f), MathUtils.random(0.3f, 0.7f), MathUtils.random(0.6f, 0.85f), MathUtils.random(0.07f, 0.2f)),
                            1f)
                    .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                    .setScaleTarget(MathUtils.random(0.2f, 4f), MathUtils.random(0.2f, 4f), 0.14f)
                    .setSpeed(MathUtils.random(0f, 80f) + xRate * -40f, MathUtils.random(0f, 80f) + yRate * -40f)
                    .setAcceleration(MathUtils.random(-6f, 6f), MathUtils.random(-6f, 6f)).setDuration(MathUtils.random(18f, 23f), true);
        }
        return FadingParticleEffect.obtain(PCLCoreImages.Effects.waterBubble.texture(), xRate * Settings.WIDTH, -EUIBase.scale(30))
                .setColor(new Color(MathUtils.random(0.3f, 0.6f), MathUtils.random(0.3f, 0.7f), MathUtils.random(0.6f, 0.85f), MathUtils.random(0f, 0.1f)),
                        new Color(MathUtils.random(0.3f, 0.5f), MathUtils.random(0.3f, 0.7f), MathUtils.random(0.6f, 0.85f), MathUtils.random(0.24f, 0.5f)),
                        1f)
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setScaleTarget(MathUtils.random(0.05f, 0.13f), MathUtils.random(0.05f, 0.13f), 0.5f)
                .setRotation(MathUtils.random(150f, 360f), MathUtils.random(15f, 50f))
                .setSpeed(MathUtils.random(-4f, 4f), MathUtils.random(70f, 130f))
                .setAcceleration(MathUtils.random(-1f, 1f), MathUtils.random(3f, 7f)).setDuration(MathUtils.random(18f, 22f), true);
    }

    public void render(SpriteBatch sb) {
        sb.end();
        renderer.setProjectionMatrix(sb.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(0, 0, Settings.WIDTH, Settings.HEIGHT, Color.TEAL, Color.TEAL, Color.BLACK, Color.BLACK);
        renderer.end();
        sb.begin();
        for (PCLEffect subEffect : subEffects) {
            subEffect.render(sb);
        }
    }

    protected void updateInternal(float deltaTime) {
        this.duration -= deltaTime;
        for (PCLEffect subEffect : subEffects) {
            subEffect.update();
            if (subEffect.isDone) {
                subEffects.remove(subEffect);
            }
        }
        if (duration < 0) {
            duration = MathUtils.random(startingDuration * 0.25f, startingDuration);
            subEffects.add(makeParticle());
        }
    }
}
