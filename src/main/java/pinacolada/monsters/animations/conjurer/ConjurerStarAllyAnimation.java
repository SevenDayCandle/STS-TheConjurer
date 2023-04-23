package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.ui.TextureCache;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.effects.vfx.FadingParticleEffect;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.PCLRenderHelpers;
import pinacolada.utilities.RandomizedList;

public class ConjurerStarAllyAnimation extends PCLAllyAnimation
{
    private static final TextureCache[] FRAMES = {ConjurerResources.conjurer.images.monsters.chaos1, ConjurerResources.conjurer.images.monsters.chaos2, ConjurerResources.conjurer.images.monsters.chaos3};
    private static final RandomizedList<TextureCache> textures = new RandomizedList<>();
    protected static final float BASE_PROJECTILE_TIMER = 0.1F;
    public static final float RADIUS = 320;

    private float projVfxTimer = BASE_PROJECTILE_TIMER;

    public ConjurerStarAllyAnimation(PCLCreature creature)
    {
        super(creature);
    }

    public void update(float deltaTime, float x, float y)
    {
        super.update(deltaTime, x, y);
        this.projVfxTimer -= deltaTime;
        if (this.projVfxTimer < 0.0F)
        {
            float r = MathUtils.random(0, 360f);
            PCLEffects.Queue.add(new FadingParticleEffect(ConjurerResources.conjurer.images.monsters.chaosOrbital.texture(), x, y)
                    .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                    .setScale(scale * MathUtils.random(0.03f, 0.15f))
                    .setRotation(0f, MathUtils.random(150f, 360f))
                    .setTargetPosition(x + RADIUS * MathUtils.cos(r), y + RADIUS * MathUtils.sin(r))).setDuration(1f, false)
                    .renderBehind = true;
            this.projVfxTimer = BASE_PROJECTILE_TIMER;
        }
    }

    public void renderSprite(SpriteBatch sb, float x, float y)
    {
        int size = FRAMES[0].texture().getHeight();
        int hSize = size / 2;
        sb.setColor(this.renderColor);
        float by = owner.getBobEffect().y / Settings.scale;
        PCLRenderHelpers.BlendingMode.Glowing.apply(sb);
        float scale1 = Interpolation.sine.apply(0.8f, 1f, angle / 105);
        sb.draw(FRAMES[0].texture(), x - hSize, y - hSize, 48f, 48f, 96f, 96f, scale1, scale1, angle, 0, 0, 96, 96, hFlip, vFlip);
        this.shineColor.a = Interpolation.sine.apply(0.4f, 0.7f, -angle / 65);
        sb.setColor(this.shineColor);
        float scale2 = Interpolation.sine.apply(0.8f, 1f, -angle / 125);
        sb.draw(FRAMES[1].texture(), x - hSize, y - hSize, 48f, 48f, 96f, 96f, scale2, scale2, angle * 0.5f, 0, 0, 96, 96, !hFlip, vFlip);
        this.shineColor.a = Interpolation.sine.apply(0.4f, 0.7f, angle / 95);
        sb.setColor(this.shineColor);
        sb.draw(FRAMES[2].texture(), x - hSize, y - hSize, 48f, 48f, 96f, 96f, scale2, scale2, -angle, 0, 0, 96, 96, !hFlip, vFlip);

        sb.setColor(Color.WHITE);
    }

    public void playActAnimation(float x, float y)
    {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.PURPLE));
    }
}
