package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.EUIUtils;
import extendedui.ui.TextureCache;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerImages;
import pinacolada.utilities.PCLRenderHelpers;

public class ConjurerFireAllyAnimation extends PCLAllyAnimation {
    public static final TextureCache[] IMAGES = {ConjurerImages.Monsters.fireParticle1, ConjurerImages.Monsters.fireParticle2, ConjurerImages.Monsters.fireParticle3};
    public static final float RADIUS = 320;

    public ConjurerFireAllyAnimation(PCLCreature creature) {
        super(creature);
    }

    public void playActAnimation(float x, float y) {
        PCLEffects.TopLevelQueue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.RED));
    }

    public void renderSprite(SpriteBatch sb, float x, float y) {
        sb.setColor(this.renderColor);
        float scaleExt = owner.getBobEffect().y / (Settings.scale * 455f);
        float scaleInt = -(owner.getBobEffect().y / (Settings.scale * 550f));
        float angleExt = this.angle;
        float angleInt = -(this.angle);
        int size = ConjurerImages.Monsters.fire1.texture().getHeight();
        int hSize = size / 2;

        sb.draw(ConjurerImages.Monsters.fire1.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleExt, this.scale + scaleExt, angleExt, 0, 0, size, size, hFlip, vFlip);
        PCLRenderHelpers.BlendingMode.Glowing.apply(sb);
        this.shineColor.a = Interpolation.sine.apply(0.1f, 0.42f, angleExt / 185) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerImages.Monsters.fire2.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleInt, this.scale + scaleInt, angleInt, 0, 0, size, size, hFlip, vFlip);
        this.shineColor.a = Interpolation.sine.apply(0.42f, 0.7f, angleInt / 185) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerImages.Monsters.fire3.texture(), x - hSize, y - hSize / 2f, hSize, hSize, size, size, this.scale + scaleInt, this.scale + scaleInt, angleInt * 2, 0, 0, size, size, hFlip, vFlip);
        sb.setColor(this.renderColor);
        PCLRenderHelpers.BlendingMode.Normal.apply(sb);

        sb.setColor(Color.WHITE);
    }

    public void updateImpl(float deltaTime, float x, float y) {
        PCLEffects.Queue.particle(EUIUtils.random(IMAGES).texture(), x + MathUtils.random(-64, 64), y + MathUtils.random(-32, 4))
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setFlip(MathUtils.randomBoolean(), false)
                .setScale(MathUtils.random(0.25f, 0.63f))
                .setRotation(0, MathUtils.random(400f, 600f))
                .setTargetPosition(x, y + RADIUS, 100f).setDuration(0.7f, false);
    }
}
