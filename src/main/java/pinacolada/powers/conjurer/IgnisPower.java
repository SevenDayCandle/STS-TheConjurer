package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.AffinityReactions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.SFX;
import pinacolada.powers.PCLPowerHelper;
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
        return AttackEffects.BURN;
    }

    @Override
    public void playApplyPowerSfx()
    {
        SFX.play(SFX.ATTACK_FIRE, 0.95f, 1.05f);
    }

    @Override
    public void onReact(AbstractCreature source, AffinityReactions reactions, int amount)
    {
        if (source == null)
        {
            source = AbstractDungeon.player;
        }
        PCLActions.bottom.applyPower(source, source, PCLCardTarget.Any, PCLPowerHelper.Vigor, (int) calculateValue(amount, getIntensifyMultiplier()), true);
        super.onReact(source, reactions, amount);
    }
}