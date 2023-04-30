package pinacolada.powers.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLSFX;
import pinacolada.misc.AffinityReactions;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

public class AerPower extends AbstractPCLElementalPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, AerPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Green);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 25);

    public AerPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(owner, source, POWER_ID, amount);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.WIND, 0.75f, 0.85f);
    }

    @Override
    public float calculateValue(int amount, float multiplier) {
        return Math.max(1, amount * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.WIND;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        PCLActions.bottom.gain(PCLElementHelper.Flow, MathUtils.ceil(calculateValue(reactions)));
        super.onReact(source, reactions);
    }
}
