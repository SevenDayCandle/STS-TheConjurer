package pinacolada.effects.vfx;


import com.badlogic.gdx.math.MathUtils;
import pinacolada.effects.PCLEffect;
import pinacolada.effects.PCLEffects;

public class ShootingStarsEffect extends PCLEffect {
    protected float x;
    protected float y;
    protected float spreadX = 55f;
    protected float spreadY = 330f;
    protected float vfxTimer;
    protected float vfxFrequency = 0.022f;
    protected float horizontalSpeedMin = 1550;
    protected float horizontalSpeedMax = 2250;
    protected float verticalSpeedMin = -190;
    protected float verticalSpeedMax = 190;
    protected boolean flipHorizontally;

    public ShootingStarsEffect(float startX, float startY) {
        super(0.8f);

        this.x = startX;
        this.y = startY;
    }

    @Override
    protected void firstUpdate(float deltaTime) {
        super.firstUpdate(deltaTime);

        if (flipHorizontally) {
            horizontalSpeedMin = -horizontalSpeedMin;
            horizontalSpeedMax = -horizontalSpeedMax;
        }
    }

    public ShootingStarsEffect flipHorizontally(boolean flip) {
        this.flipHorizontally = flip;

        return this;
    }

    public ShootingStarsEffect setFrequency(float frequency) {
        this.vfxFrequency = MathUtils.clamp(frequency, 0.01f, startingDuration / 5f);

        return this;
    }

    public ShootingStarsEffect setHorizontalSpeed(float min, float max) {
        this.horizontalSpeedMin = min;
        this.horizontalSpeedMax = max;

        return this;
    }

    public ShootingStarsEffect setSpread(float spreadX, float spreadY) {
        this.spreadX = spreadX;
        this.spreadY = spreadY;

        return this;
    }

    public ShootingStarsEffect setVerticalSpeed(float min, float max) {
        this.horizontalSpeedMin = min;
        this.horizontalSpeedMax = max;

        return this;
    }

    @Override
    protected void updateInternal(float deltaTime) {
        vfxTimer -= deltaTime;
        if (vfxTimer < 0f) {
            final float x = this.x + random(-spreadX, spreadX);
            final float y = this.y + random(-spreadY, spreadY);
            final float h_speed = random(horizontalSpeedMin, horizontalSpeedMax);
            final float v_speed = random(verticalSpeedMin, verticalSpeedMax);
            PCLEffects.Queue.add(new StarEffect(x, y, h_speed, v_speed));
            vfxTimer = vfxFrequency;
        }

        super.updateInternal(deltaTime);
    }
}
