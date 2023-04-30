package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLSFX;
import pinacolada.misc.AffinityReactions;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

public class PetraPower extends AbstractPCLElementalPower {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, PetraPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Orange);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 25);

    public PetraPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(owner, source, POWER_ID, amount);
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.PCL_ORB_EARTH_CHANNEL, 0.95f, 1.05f);
    }

    @Override
    public float calculateValue(int amount, float multiplier) {
        return Math.max(1, amount * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.EARTH;
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions) {
        PCLActions.bottom.applyPower(source, PCLCardTarget.Single, PCLPowerHelper.NextTurnBlock, (int) calculateValue(reactions));
        super.onReact(source, reactions);
    }
}
