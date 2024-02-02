package pinacolada.interfaces.listeners;

import com.megacrit.cardcrawl.core.AbstractCreature;
import pinacolada.powers.conjurer.AbstractElementPower;

public interface OnElementalDebuffListener {
    float getPercentage(float initial, AbstractElementPower element, AbstractCreature owner);
}
