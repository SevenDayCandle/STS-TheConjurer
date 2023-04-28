package pinacolada.powers.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.dungeon.CombatManager;
import pinacolada.effects.PCLSFX;
import pinacolada.interfaces.subscribers.OnTryElementReactSubscriber;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

public class LuxPower extends AbstractPCLElementalPower implements OnTryElementReactSubscriber {
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, LuxPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Yellow);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 40);

    public LuxPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(owner, source, POWER_ID, amount);
    }

    @Override
    public void onRemove() {
        super.onRemove();

        CombatManager.unsubscribe(this);
    }

    @Override
    public int onTryElementReact(int amount, PCLAffinity button, PCLAffinity trigger) {
        return (int) calculateValue(amount, getIntensifyMultiplier());
    }

    public float calculateValue(int amount, float multiplier) {
        return amount + MathUtils.ceil(amount * (multiplier / 100f));
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

        CombatManager.subscribe(this);
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect() {
        return PCLEnum.AttackEffect.ELECTRIC;
    }

    @Override
    public void playApplyPowerSfx() {
        PCLSFX.play(PCLSFX.ORB_LIGHTNING_PASSIVE, 0.95f, 1.05f);
    }
}
