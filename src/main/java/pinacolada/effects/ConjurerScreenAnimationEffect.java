package pinacolada.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class ConjurerScreenAnimationEffect extends PCLEffect {
    protected static final int MAX = 30;

    public final ArrayList<PCLEffect> subEffects = new ArrayList<>();

    public ConjurerScreenAnimationEffect() {

    }

    protected void firstUpdate() {
    }

    public void render(SpriteBatch sb) {
        for (PCLEffect subEffect : subEffects) {
            subEffect.render(sb);
        }
    }

    protected void updateInternal(float deltaTime) {
        this.duration -= deltaTime;
        for (PCLEffect subEffect : subEffects) {
            subEffect.update();
        }
        subEffects.removeIf(subEffect -> subEffect.isDone);
        if (duration < 0) {
            duration = startingDuration;
            if (subEffects.size() < MAX) {
                // TODO
            }
        }
    }

}
