package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.effects.vfx.FireIgniteEffect;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

public class ConjurerFireAllyAnimation extends PCLAllyAnimation
{
    public static final float RADIUS = 320;

    public ConjurerFireAllyAnimation(PCLCreature creature)
    {
        super(creature);
    }

    public void updateImpl(float deltaTime, float x, float y)
    {
        PCLEffects.Queue.add(new FadingParticleEffect(FireIgniteEffect.getRandomTexture(), x + MathUtils.random(-64, 64), y + MathUtils.random(-32, 4))
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setFlip(MathUtils.randomBoolean(), false)
                .setScale(MathUtils.random(0.2f, 0.5f))
                .setRotation(0, MathUtils.random(400f, 600f))
                .setTargetPosition(x, y + RADIUS, 100f)).setDuration(0.6f, false);
    }

    public void renderSprite(SpriteBatch sb, float x, float y)
    {
        sb.setColor(this.renderColor);
        float scaleExt = owner.getBobEffect().y / 455f;
        float scaleInt = -(owner.getBobEffect().y / 550f);
        float angleExt = this.angle;
        float angleInt = -(this.angle);
        int size = ConjurerResources.conjurer.images.monsters.fireInternal.texture().getHeight();
        int hSize = size / 2;

        sb.draw(ConjurerResources.conjurer.images.monsters.fireInternal.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt, this.scale + scaleExt, angleExt, 0, 0, size, size, hFlip, vFlip);
        sb.setBlendFunction(770, 1);
        this.shineColor.a = Interpolation.sine.apply(0.1f, 0.42f, angleExt / 185) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.fireExternal.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleInt, this.scale + scaleInt, angleInt, 0, 0, size, size, hFlip, vFlip);
        this.shineColor.a = Interpolation.sine.apply(0.42f, 0.7f, angleInt / 185) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.fireExternal2.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleInt, this.scale + scaleInt, angleInt * 2, 0, 0, size, size, hFlip, vFlip);
        sb.setColor(this.renderColor);
        sb.setBlendFunction(770, 771);

        sb.setColor(Color.WHITE);
    }

    public void playActAnimation(float x, float y)
    {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.RED));
    }
}
