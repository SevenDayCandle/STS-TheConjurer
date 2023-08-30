package pinacolada.monsters.animations.conjurer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import extendedui.EUIUtils;
import extendedui.ui.TextureCache;
import pinacolada.effects.PCLEffects;
import pinacolada.effects.VFX;
import pinacolada.monsters.PCLCreature;
import pinacolada.monsters.animations.PCLAllyAnimation;
import pinacolada.resources.conjurer.ConjurerImages;

import static extendedui.EUIGameUtils.scale;

public class ConjurerEarthAllyAnimation extends PCLAllyAnimation {
    public static final TextureCache[] IMAGES = {ConjurerImages.Monsters.earthParticle1, ConjurerImages.Monsters.earthParticle2, ConjurerImages.Monsters.earthParticle3};
    public static final float RADIUS = Settings.scale * 55;

    public ConjurerEarthAllyAnimation(PCLCreature creature) {
        super(creature,0.3f, 0.4f);
    }

    public void playActAnimation(float x, float y) {
        PCLEffects.Queue.add(VFX.circularWave(x, y).setScale(0.25f, 12f).setColors(Color.WHITE, Color.ORANGE));
    }

    public void renderSprite(SpriteBatch sb, float x, float y) {
        sb.setColor(this.renderColor);
        int size = ConjurerImages.Monsters.earth1.texture().getHeight();
        float angleExt = this.angle / 8f;
        float rSize = Settings.scale * size;
        float hSize = rSize / 2;

        sb.draw(ConjurerImages.Monsters.earth1.texture(), x - hSize, y - hSize * 0.6f, hSize, hSize, rSize, rSize, this.scale, this.scale, angleExt, 0, 0, size, size, hFlip, vFlip);

        sb.setColor(Color.WHITE);
    }

    public void updateImpl(float deltaTime, float x, float y) {
        PCLEffects.Queue.particle(EUIUtils.random(IMAGES).texture(), x + MathUtils.random(-RADIUS, RADIUS), y + MathUtils.random(scale(-46), scale(5)))
                .setFlip(MathUtils.randomBoolean(), false)
                .setScale(MathUtils.random(0.09f, 0.32f))
                .setRotation(0, MathUtils.random(300f, 500f))
                .setSpeed(0, scale(55))
                .setDuration(1f, false);
    }
}
