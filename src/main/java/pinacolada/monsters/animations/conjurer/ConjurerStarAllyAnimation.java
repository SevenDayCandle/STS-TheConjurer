package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import extendedui.EUIRenderHelpers;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreImages;
import pinacolada.utilities.PCLRenderHelpers;

public class ConjurerStarAllyAnimation extends PCLAllyAnimation {
    public static final float RADIUS = 320;
    public static final Color START_COLOR = new Color(0.9f, 0.65f, 1, 0.8f);
    public static final Color TARGET_COLOR = new Color(0.74f, 0.3f, 1, 0.7f);
    protected static final float BASE_FLASH_TIMER = 0.4F;
    private float flashTimer = BASE_FLASH_TIMER;

    public ConjurerStarAllyAnimation(PCLCreature creature) {
        super(creature, 0.1f, 0.1f);
    }

    public void playActAnimation(float x, float y) {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.PURPLE));
    }

    public void update(float deltaTime, float x, float y) {
        super.update(deltaTime, x, y);
        this.flashTimer -= deltaTime;
        if (this.flashTimer < 0.0F) {
            PCLEffects.Queue.add(new FadingParticleEffect(PCLCoreImages.Effects.star2.texture(), x, y)
                    .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                    .setColor(START_COLOR, TARGET_COLOR, 1.8f)
                    .setRotation(angle, 120f)
                    .setScale(0.1f, 0.4f, 0.2f)
                    .setDuration(1.8f, false)).renderBehind = true;
            this.flashTimer = BASE_FLASH_TIMER;
        }
    }

    public void updateImpl(float deltaTime, float x, float y) {
        float r = MathUtils.random(0, 360f);
        PCLEffects.Queue.add(new FadingParticleEffect(ConjurerResources.conjurer.images.monsters.chaosOrbital.texture(), x, y)
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setScale(scale * MathUtils.random(0.09f, 0.25f))
                .setRotation(0f, MathUtils.random(150f, 360f))
                .setTargetPosition(x + RADIUS * MathUtils.cos(r), y + RADIUS * MathUtils.sin(r))).setDuration(1f, false)
                .renderBehind = true;
    }

    public void renderSprite(SpriteBatch sb, float x, float y) {
        int size = ConjurerResources.conjurer.images.monsters.chaos1.texture().getHeight();
        int hSize = size / 2;
        sb.setColor(this.renderColor);
        float scale1 = Interpolation.sine.apply(0.46f, 0.52f, angle / 125);
        PCLRenderHelpers.BlendingMode.Overlay.apply(sb);
        sb.draw(ConjurerResources.conjurer.images.monsters.chaos1.texture(), x - hSize, y - hSize / 2.3f, hSize, hSize, size, size, scale1, scale1, 0, 0, 0, size, size, hFlip, vFlip);
        this.shineColor.a = Interpolation.sine.apply(0.2f, 0.6f, -angle / 45);
        sb.setColor(this.shineColor);
        PCLRenderHelpers.BlendingMode.Glowing.apply(sb);
        EUIRenderHelpers.drawGlitched(sb, s -> {
            float scale2 = Interpolation.sine.apply(0.46f, 0.52f, angle / 125);
            s.draw(ConjurerResources.conjurer.images.monsters.chaos2.texture(), x - hSize, y - hSize / 2.3f, hSize, hSize, size, size, scale2, scale2, 0, 0, 0, size, size, hFlip, vFlip);
            this.shineColor.a = Interpolation.sine.apply(0.3f, 0.5f, angle / 45);
            s.setColor(this.shineColor);
            s.draw(ConjurerResources.conjurer.images.monsters.chaos3.texture(), x - hSize, y - hSize / 2.3f, hSize, hSize, size, size, scale2, scale2, 0, 0, 0, size, size, hFlip, vFlip);
        });
        PCLRenderHelpers.BlendingMode.Normal.apply(sb);
        sb.setColor(Color.WHITE);
    }
}
