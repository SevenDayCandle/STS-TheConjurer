package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.SFX;
import pinacolada.misc.AffinityReactions;
import pinacolada.monsters.PCLCardAlly;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerResources;

public class IgnisPower extends AbstractPCLElementalPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, IgnisPower.class);
    public static final PCLAffinity AFFINITY = setAffinity(POWER_ID, PCLAffinity.Red);
    public static final int MULTIPLIER = setMultiplier(POWER_ID, 25);

    public IgnisPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID, amount);
    }

    public float calculateValue(int amount, float multiplier)
    {
        return Math.max(1, amount * (multiplier / 100f));
    }

    @Override
    public AbstractGameAction.AttackEffect getAttackEffect()
    {
        return PCLEnum.AttackEffect.BURN;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ATTACK_FIRE, 0.95f, 1.05f);
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions, int amount)
    {
        if (!(owner instanceof PCLCardAlly))
        {
            PCLActions.bottom.applyPower(source, owner, PCLCardTarget.Single, PCLElementHelper.Blasted, (int) calculateValue(amount, getIntensifyMultiplier()));
        }
        super.onReact(source, reactions, amount);
    }
}