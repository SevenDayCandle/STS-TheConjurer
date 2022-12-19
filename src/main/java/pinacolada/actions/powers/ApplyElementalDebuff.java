package pinacolada.actions.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.interfaces.delegates.ActionT1;
import pinacolada.actions.PCLActionAutoTarget;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.powers.conjurer.PCLElementHelper;

public class ApplyElementalDebuff extends PCLActionAutoTarget<AbstractPower>
{
    public static final String[] TEXT = ApplyPowerAction.TEXT;

    protected PCLElementHelper helper;

    protected boolean chooseRandomTarget;
    protected boolean ignoreArtifact;
    protected boolean allowNegative = false;
    protected boolean showEffect = true;
    protected boolean skipIfZero = true;
    protected boolean skipIfNull = true;
    protected boolean canStack = true;
    protected boolean temporary = false;
    protected boolean faster;

    public ApplyElementalDebuff(AbstractCreature source, AbstractCreature target, PCLCardTarget targetHelper, PCLAffinity helper, int amount)
    {
        this(source, target, targetHelper, PCLElementHelper.get(helper), amount, 1);
    }

    public ApplyElementalDebuff(AbstractCreature source, AbstractCreature target, PCLCardTarget targetHelper, PCLElementHelper helper, int amount)
    {
        this(source, target, targetHelper, helper, amount, 1);
    }

    public ApplyElementalDebuff(AbstractCreature source, AbstractCreature target, PCLCardTarget targetHelper, PCLElementHelper helper, int amount, int limit)
    {
        super(AbstractGameAction.ActionType.POWER);

        this.helper = helper;

        initialize(source, target, targetHelper, amount, limit);

        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead() || this.helper == null)
        {
            complete();
        }
    }

    public ApplyElementalDebuff canStack(boolean canStack)
    {
        this.canStack = canStack;

        return this;
    }

    @Override
    protected void firstUpdate()
    {
        for (AbstractCreature target : findTargets(true)) // Reverse because of GameActions.Top
        {
            ApplyPower action = new ApplyPower(source, target, helper.create(target, source, amount), amount);
            action.ignoreArtifact(ignoreArtifact);
            action.setRealtime(isRealtime);
            action.showEffect(showEffect, faster);
            action.skipIfZero(skipIfZero);
            action.skipIfNull(skipIfNull);
            action.canStack(canStack);
            action.isCancellable(canCancel);
            action.allowNegative(allowNegative);

            PCLActions.top.add(action).addCallback((ActionT1<AbstractPower>) this::complete);
        }

        complete();
    }

    public ApplyElementalDebuff ignoreArtifact(boolean ignoreArtifact)
    {
        this.ignoreArtifact = ignoreArtifact;

        return this;
    }

    public ApplyElementalDebuff setTemporary(boolean temporary)
    {
        this.temporary = temporary;

        return this;
    }

    public ApplyElementalDebuff showEffect(boolean showEffect, boolean isFast)
    {
        this.showEffect = showEffect;
        this.faster = isFast;

        return this;
    }

    public ApplyElementalDebuff skipIfNull(boolean skipIfNull)
    {
        this.skipIfNull = skipIfNull;

        return this;
    }

    public ApplyElementalDebuff skipIfZero(boolean skipIfZero)
    {
        this.skipIfZero = skipIfZero;

        return this;
    }

    public ApplyElementalDebuff allowNegative(boolean allowNegative)
    {
        this.allowNegative = allowNegative;

        return this;
    }
}
