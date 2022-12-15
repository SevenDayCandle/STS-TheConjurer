package pinacolada.powers.conjurer;

import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import extendedui.EUIUtils;
import pinacolada.actions.PCLActions;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.utilities.GameUtilities;

public class FrozenPower extends PCLPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, FrozenPower.class);
    public static final int MULTIPLIER = 100;

    public FrozenPower(AbstractCreature owner, int amount)
    {
        super(owner, POWER_ID);

        initialize(amount, PowerType.DEBUFF, false);
    }

    @Override
    public void onInitialApplication()
    {
        super.onInitialApplication();

        final AbstractMonster monster = EUIUtils.safeCast(owner, AbstractMonster.class);
        if (monster != null)
        {
            PCLActions.bottom.applyPower(owner, owner, new StunMonsterPower(monster));
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type, AbstractCard card)
    {
        return type == DamageInfo.DamageType.NORMAL && GameUtilities.getPCLCardAffinityLevel(card, PCLAffinity.Red, true) > 0 ? damage * (1 + getMultiplier()/100f) : damage;
    }

    @Override
    public String getUpdatedDescription()
    {
        return formatDescription(0, getMultiplier());
    }

    public int getMultiplier()
    {
        return amount * MULTIPLIER;
    }
}
