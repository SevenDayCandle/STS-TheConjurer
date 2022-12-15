package pinacolada.interfaces.listeners;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.powers.conjurer.AbstractPCLElementalPower;

public interface OnElementalDebuffListener
{
    float getPercentage(float initial, AbstractPCLElementalPower element, AbstractCreature owner);
}
