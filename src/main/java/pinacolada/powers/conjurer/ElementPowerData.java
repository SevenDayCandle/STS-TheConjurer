package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.PCLResources;
import pinacolada.resources.conjurer.ConjurerResources;

import java.util.HashMap;

public class ElementPowerData extends PCLPowerData {
    private static final HashMap<PCLAffinity, ElementPowerData> AFFINITY_TO_ELEMENT = new HashMap<>();
    public PCLAffinity affinity;

    public ElementPowerData(Class<? extends AbstractPower> invokeClass, PCLAffinity affinity) {
        this(invokeClass, ConjurerResources.conjurer, affinity);
    }

    public ElementPowerData(Class<? extends AbstractPower> invokeClass, PCLResources<?, ?, ?, ?> resources, PCLAffinity affinity) {
        super(invokeClass, resources);
        this.endTurnBehavior = Behavior.Special;
        this.priority = 4;
        this.affinity = affinity;
        AFFINITY_TO_ELEMENT.put(affinity, this);
    }

    public static ElementPowerData get(PCLAffinity aff) {
        return AFFINITY_TO_ELEMENT.getOrDefault(aff, LuxPower.DATA);
    }

    public ElementPowerData setTooltip(EUIKeywordTooltip tip) {
        super.setTooltip(tip);
        return this;
    }

    public ElementPowerData setType(AbstractPower.PowerType tip) {
        super.setType(tip);
        return this;
    }
}
