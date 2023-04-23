package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.EUIRenderHelpers;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.effects.vfx.SnowBurstEffect;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;

public class ConjurerWaterAllyAnimation extends PCLAllyAnimation
{
    public static final float RADIUS = 320;

    public ConjurerWaterAllyAnimation(PCLCreature creature)
    {
        super(creature);
    }

    public void updateImpl(float deltaTime, float x, float y)
    {
        PCLEffects.Queue.add(new FadingParticleEffect(SnowBurstEffect.getRandomTexture(), x, y)
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setScale(MathUtils.random(0.2f, 0.5f))
                .setRotation(0, MathUtils.random(150f, 360f))
                .setTargetPosition(x + RADIUS * MathUtils.cos(angle), y + RADIUS * MathUtils.sin(angle), 100f)
        ).setDuration(1f, false)
                .renderBehind = true;
    }

    public void renderSprite(SpriteBatch sb, float x, float y)
    {
        sb.setColor(this.renderColor);
        float scaleExt = owner.getBobEffect().y / (Settings.scale * 575f);
        float scaleExt2 = owner.getBobEffect().y / (Settings.scale * 400f);
        float scaleInt = -(owner.getBobEffect().y / (Settings.scale * 500f));
        float angleExt = this.angle * 4f;
        float angleExt2 = this.angle * 5f;
        float angleInt = -(this.angle);
        int size = ConjurerResources.conjurer.images.monsters.water1.texture().getHeight();
        int hSize = size / 2;

        this.shineColor.a = Interpolation.sine.apply(0.05f, 0.55f, angleExt / 135);
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.water3.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt2, this.scale + scaleExt2, angleExt * 1.2f, 0, 0, size, size, hFlip, vFlip);

        this.shineColor.a = Interpolation.sine.apply(0.12f, 0.72f, angleExt2 / 185);
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.water4.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt2, this.scale + scaleExt2, -angleExt2 * 0.7f, 0, 0, size, size, !hFlip, vFlip);

        sb.setColor(this.renderColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.water1.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleInt, this.scale + scaleInt, angleInt * 1.3f, 0, 0, size, size, hFlip, vFlip);

        PCLRenderHelpers.setBlending(sb, EUIRenderHelpers.BlendingMode.Glowing);
        this.shineColor.a = Interpolation.sine.apply(0.22f, 0.42f, angleExt / 165);
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.water2.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt, this.scale + scaleExt, -angleExt * 1.4f, 0, 0, size, size, hFlip, vFlip);
        PCLRenderHelpers.setBlending(sb, EUIRenderHelpers.BlendingMode.Normal);

        sb.setColor(Color.WHITE);
    }

    public void playActAnimation(float x, float y)
    {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.BLUE));
    }
}
