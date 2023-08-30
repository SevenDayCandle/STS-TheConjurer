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

public class ConjurerAirAllyAnimation extends PCLAllyAnimation {
    public static final TextureCache[] IMAGES = {ConjurerImages.Monsters.airCloud1, ConjurerImages.Monsters.airCloud2};
    public static final float RADIUS = Settings.scale * 240;

    public ConjurerAirAllyAnimation(PCLCreature creature) {
        super(creature,0.3f, 0.4f);
    }

    public void playActAnimation(float x, float y) {
        PCLEffects.Queue.add(VFX.circularWave(x, y).setScale(0.25f, 10f).setColors(Color.WHITE, Color.GREEN));
    }

    public void renderSprite(SpriteBatch sb, float x, float y) {
        sb.setColor(this.renderColor);
        int size = ConjurerImages.Monsters.air1.texture().getHeight();
        float scaleExt1 = owner.getBobEffect().y / (Settings.scale * 557f);
        float scaleExt2 = owner.getBobEffect().y / (Settings.scale * 300f);
        float angleExt1 = this.angle * 5f;
        float angleExt2 = this.angle * 7f;
        float rSize = Settings.scale * size;
        float hSize = rSize / 2;

        sb.draw(ConjurerImages.Monsters.air1.texture(), x - hSize, y - hSize * 0.6f, hSize, hSize, rSize, rSize, this.scale + scaleExt1, this.scale + scaleExt1, angleExt1, 0, 0, size, size, hFlip, vFlip);
        sb.draw(ConjurerImages.Monsters.air2.texture(), x - hSize, y - hSize * 0.6f, hSize, hSize, rSize, rSize, this.scale + scaleExt2, this.scale + scaleExt2, angleExt2, 0, 0, size, size, !hFlip, vFlip);

        this.shineColor.a = Interpolation.sine.apply(0.1f, 0.33f, angleExt2 / 185) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerImages.Monsters.air1.texture(), x - hSize, y - hSize * 0.6f, hSize, hSize, rSize, rSize, this.scale + scaleExt1, this.scale + scaleExt1, this.angle * 1.8f, 0, 0, size, size, hFlip, vFlip);

        this.shineColor.a = Interpolation.sine.apply(0.1f, 0.33f, angleExt1 / 135) * this.transitionAlpha;
        sb.setColor(this.shineColor);
        sb.draw(ConjurerImages.Monsters.air3.texture(), x - hSize, y - hSize * 0.6f, hSize, hSize, rSize, rSize, this.scale + scaleExt2, this.scale + scaleExt2, this.angle * 1.2f, 0, 0, size, size, hFlip, vFlip);

        sb.setColor(Color.WHITE);
    }

    public void updateImpl(float deltaTime, float x, float y) {
        PCLEffects.Queue.particle(EUIUtils.random(IMAGES).texture(), x, y)
                .setBlendingMode(PCLRenderHelpers.BlendingMode.Glowing)
                .setColor(new Color(MathUtils.random(0.7f, 1f), 1, MathUtils.random(0.8f, 1f), MathUtils.random(0.5f, 0.8f)))
                .setScale(MathUtils.random(0.1f, 0.35f))
                .setRotation(0f, MathUtils.random(450f, 650f))
                .setTargetPosition(x + RADIUS * MathUtils.cos(angle), y + RADIUS * MathUtils.sin(angle), 100f
                ).setDuration(1f, false).renderBehind = true;
    }
}
