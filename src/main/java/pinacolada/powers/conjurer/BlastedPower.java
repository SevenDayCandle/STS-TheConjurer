package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.powers.PCLPower;
import pinacolada.resources.conjurer.ConjurerResources;

public class BlastedPower extends PCLPower
{
    public static final String POWER_ID = createFullID(ConjurerResources.conjurer, BlastedPower.class);

    public BlastedPower(AbstractCreature owner, AbstractCreature source, int amount)
    {
        super(owner, source, POWER_ID);
        initialize(amount, PowerType.DEBUFF, true);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type)
    {
        return super.atDamageReceive(type == DamageInfo.DamageType.NORMAL ? damage + amount : damage, type);
    }

    public int onAttacked(DamageInfo info, int damageAmount)
    {
        if (damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL)
        {
            removePower();
        }
        return damageAmount;
    }
}
