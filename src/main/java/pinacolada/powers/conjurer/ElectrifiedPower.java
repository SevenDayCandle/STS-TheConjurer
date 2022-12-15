package pinacolada.powers.conjurer;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.interfaces.subscribers.OnTryElementReactSubscriber;
import pinacolada.misc.CombatManager;
import pinacolada.resources.conjurer.ConjurerResources;

public class ElectrifiedPower extends AbstractPCLElementalPower implements OnTryElementReactSubscriber
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, ElectrifiedPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Light);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 40);

    public ElectrifiedPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public static int calculateBonus(int bonus, float multiplier)
    {
        return bonus + MathUtils.ceil(bonus * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return AttackEffects.ELECTRIC;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ORB_LIGHTNING_PASSIVE, 0.95f, 1.05f);
    }

    @Override
    public void onInitialApplication()
    {
        super.onInitialApplication();

        CombatManager.onTryElementReact.subscribe(this);
    }

    @Override
    public void onRemove()
    {
        super.onRemove();

        CombatManager.onTryElementReact.unsubscribe(this);
    }

    @Override
    public int onTryElementReact(int amount, PCLAffinity button, PCLAffinity trigger)
    {
        return calculateBonus(amount, getIntensifyMultiplier());
    }
}
