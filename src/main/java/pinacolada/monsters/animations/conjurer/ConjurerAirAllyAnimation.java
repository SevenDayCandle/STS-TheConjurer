package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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

public class ConjurerAirAllyAnimation extends PCLAllyAnimation {
    private static final TextureCache[] particles = {ConjurerResources.conjurer.images.monsters.airCloud1, ConjurerResources.conjurer.images.monsters.airCloud2};
    private static final RandomizedList<TextureCache> textures = new RandomizedList<>();
    public static final float RADIUS = 320;

    public ConjurerAirAllyAnimation(PCLCreature creature) {
        super(creature);
    }

    public static Texture getRandomTexture() {
        if (textures.size() <= 1) // Adds some randomness but still ensures all textures are cycled through
        {
            textures.addAll(particles);
        }

        return textures.retrieveUnseeded(true).texture();
    }

    public void playActAnimation(float x, float y) {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.GREEN));
    }

    public void renderSprite(SpriteBatch sb, float x, float y) {
        sb.setColor(this.renderColor);
        float scaleExt1 = owner.getBobEffect().y / (Settings.scale * 557f);
        float scaleExt2 = owner.getBobEffect().y / (Settings.scale * 300f);
        float angleExt1 = this.angle * 5f;
        float angleExt2 = this.angle * 7f;
        int size = ConjurerResources.conjurer.images.monsters.air1.texture().getHeight();
        int hSize = size / 2;

        sb.draw(ConjurerResources.conjurer.images.monsters.air1.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt1, this.scale + scaleExt1, angleExt1, 0, 0, size, size, hFlip, vFlip);
        sb.draw(ConjurerResources.conjurer.images.monsters.air2.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt2, this.scale + scaleExt2, angleExt2, 0, 0, size, size, !hFlip, vFlip);

        this.shineColor.a = Interpolation.sine.apply(0.1f, 0.33f, angleExt2 / 185) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.air1.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt1, this.scale + scaleExt1, this.angle * 1.8f, 0, 0, size, size, hFlip, vFlip);

        this.shineColor.a = Interpolation.sine.apply(0.1f, 0.33f, angleExt1 / 135) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerResources.conjurer.images.monsters.air3.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt2, this.scale + scaleExt2, this.angle * 1.2f, 0, 0, size, size, hFlip, vFlip);

        sb.setColor(Color.WHITE);
    }

    public void updateImpl(float deltaTime, float x, float y) {
        PCLEffects.Queue.add(new FadingParticleEffect(getRandomTexture(), x, y)
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setColor(new Color(MathUtils.random(0.7f, 1f), 1, MathUtils.random(0.8f, 1f), MathUtils.random(0.5f, 0.8f)))
                .setScale(MathUtils.random(0.1f, 0.35f))
                .setRotation(0f, MathUtils.random(450f, 650f))
                .setTargetPosition(x + RADIUS * MathUtils.cos(angle), y + RADIUS * MathUtils.sin(angle), 100f)
        ).setDuration(1f, false).renderBehind = true;
    }
}
