package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.effects.vfx.RockBurstEffect;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerResources;

public class ConjurerEarthAllyAnimation extends PCLAllyAnimation
{
    public static final float RADIUS = 320;

    public ConjurerEarthAllyAnimation(PCLCreature creature)
    {
        super(creature);
    }

    public void updateImpl(float deltaTime, float x, float y)
    {
        PCLEffects.Queue.add(new FadingParticleEffect(RockBurstEffect.getRandomTexture(), x + MathUtils.random(-64, 64), y + MathUtils.random(-32, 4))
                .setFlip(MathUtils.randomBoolean(), false)
                .setScale(MathUtils.random(0.09f, 0.64f))
                .setRotation(0, MathUtils.random(400f, 600f))
                .setTargetPosition(x, y + RADIUS, 50f)).setDuration(0.6f, false);
    }

    public void renderSprite(SpriteBatch sb, float x, float y)
    {
        sb.setColor(this.renderColor);
        float angleExt = this.angle / 13f;
        int size = ConjurerResources.conjurer.images.monsters.earth1.texture().getHeight();
        int hSize = size / 2;

        sb.draw(ConjurerResources.conjurer.images.monsters.earth1.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale, this.scale, angleExt, 0, 0, size, size, hFlip, vFlip);

        sb.setColor(Color.WHITE);
    }

    public void playActAnimation(float x, float y)
    {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.ORANGE));
    }
}
