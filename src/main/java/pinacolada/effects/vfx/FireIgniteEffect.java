package pinacolada.effects.vfx;

import com.badlogic.gdx.math.MathUtils;
import extendedui.EUIUtils;
import extendedui.utilities.EUIColors;
import pinacolada.effects.PCLEffect;
import pinacolada.effects.PCLEffects;
import pinacolada.monsters.animations.conjurer.ConjurerFireAllyAnimation;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.utilities.PCLRenderHelpers;

public class FireIgniteEffect extends PCLEffect {
    public static final float RADIUS = 320;
    protected float x;
    protected float y;

    public FireIgniteEffect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void firstUpdate(float deltaTime) {
        float r = MathUtils.random(0, 360);
        PCLEffects.Queue.particle(ConjurerImages.Effects.fireBurst.texture(), x + MathUtils.random(-16, 16), y + MathUtils.random(-16, 16))
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setColor(EUIColors.white(MathUtils.random(0.5f, 1f)))
                .setFlip(MathUtils.randomBoolean(), false)
                .setScale(scale * MathUtils.random(0.2f, 0.6f))
                .setRotation(0f, MathUtils.random(150f, 360f))
                .setTargetPosition(x + RADIUS * MathUtils.cosDeg(r), y + RADIUS * MathUtils.sinDeg(r)).setDuration(0.6f, false);
        for (int i = 0; i < 25; ++i) {
            r = MathUtils.random(0, 360);
            PCLEffects.Queue.particle(EUIUtils.random(ConjurerFireAllyAnimation.IMAGES).texture(), x + MathUtils.random(-16, 16), y + MathUtils.random(-16, 16))
                    .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                    .setColor(EUIColors.white(MathUtils.random(0.5f, 1f)))
                    .setFlip(MathUtils.randomBoolean(), false)
                    .setScale(scale * MathUtils.random(0.09f, 0.5f))
                    .setRotation(0f, MathUtils.random(150f, 360f))
                    .setTargetPosition(x + RADIUS * MathUtils.cosDeg(r), y + RADIUS * MathUtils.sinDeg(r)).setDuration(0.6f, false);
        }

        complete();
    }
}
