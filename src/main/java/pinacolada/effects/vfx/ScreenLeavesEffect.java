package pinacolada.effects.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
import extendedui.EUIUtils;
import pinacolada.effects.PCLEffect;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.PCLSFX;

public class ScreenLeavesEffect extends PCLEffect {
    private static final float INTERVAL = 0.05F;
    private static final int TIMES = 9;
    private float timer = 0.0F;

    public ScreenLeavesEffect() {
        this.color = Color.FOREST.cpy();
        this.duration = 3.0F;
        this.startingDuration = this.duration;
    }

    public void dispose() {
    }

    protected void onTrail(TrailingParticleEffect trail) {
        if (MathUtils.randomBoolean()) {
            PCLEffects.Queue.add(new LightFlareParticleEffect(trail.x, trail.y, color));
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            PCLSFX.play(PCLSFX.HEAL_3);
            PCLEffects.Queue.add(new BorderLongFlashEffect(color));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer -= Gdx.graphics.getDeltaTime();
        if (this.timer < 0.0F) {
            for (int i = 0; i < TIMES; i++) {
                PCLEffects.Queue.trail(EUIUtils.random(TornadoEffect.IMAGES_AIR).texture(), this::onTrail, MathUtils.random(-50 * Settings.scale, -100 * Settings.scale), MathUtils.random(0.0F, (float) Settings.HEIGHT))
                        .setSpeed(MathUtils.random(850.0F, 1750.0F) * Settings.scale, MathUtils.random(-70.0F, 70.0F) * Settings.scale)
                        .setRotation(random(-100f, 100f), random(-600f, 600f))
                        .setScale(random(0.1f, 0.8f) * Settings.scale)
                        .setFlip(randomBoolean(0.5f), false)
                        .setDuration(0.75f, isRealtime);
            }
            this.timer = 0.05F;
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }
}
