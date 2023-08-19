package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import extendedui.interfaces.delegates.FuncT1;
import extendedui.interfaces.delegates.FuncT3;
import extendedui.ui.tooltips.EUIKeywordTooltip;
import org.apache.commons.lang3.StringUtils;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.utilities.GameUtilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PCLElementHelper extends PCLPowerHelper {
    private static final Map<String, PCLElementHelper> ALL = new HashMap<>();
    private static final Map<PCLAffinity, PCLElementHelper> ALL_BY_AFFINITY = new HashMap<>();

    public static final PCLElementHelper Aqua = new PCLElementHelper(AquaPower.POWER_ID, ConjurerResources.conjurer.tooltips.aqua, AquaPower::new, AquaPower.AFFINITY);
    public static final PCLElementHelper Ignis = new PCLElementHelper(IgnisPower.POWER_ID, ConjurerResources.conjurer.tooltips.ignis, IgnisPower::new, IgnisPower.AFFINITY);
    public static final PCLElementHelper Lux = new PCLElementHelper(LuxPower.POWER_ID, ConjurerResources.conjurer.tooltips.lux, LuxPower::new, LuxPower.AFFINITY);
    public static final PCLElementHelper Petra = new PCLElementHelper(PetraPower.POWER_ID, ConjurerResources.conjurer.tooltips.petra, PetraPower::new, PetraPower.AFFINITY);
    public static final PCLElementHelper Ventus = new PCLElementHelper(VentusPower.POWER_ID, ConjurerResources.conjurer.tooltips.ventus, VentusPower::new, VentusPower.AFFINITY);
    public static final PCLPowerHelper Blasted = new PCLPowerHelper(BlastedPower.POWER_ID, ConjurerResources.conjurer.tooltips.blasted, BlastedPower::new, Behavior.TurnBased, false, true, false, false);
    public static final PCLPowerHelper Cooled = new PCLPowerHelper(CooledPower.POWER_ID, ConjurerResources.conjurer.tooltips.cooled, (o, s, a) -> new CooledPower(o, a), Behavior.TurnBased, false, true, false, false);
    public static final PCLPowerHelper Flow = new PCLPowerHelper(FlowPower.POWER_ID, ConjurerResources.conjurer.tooltips.flow, FlowPower::new, Behavior.Permanent, false, false, false, false);
    public final PCLAffinity affinity;

    public PCLElementHelper(String id, EUIKeywordTooltip tooltip, FuncT3<AbstractPower, AbstractCreature, AbstractCreature, Integer> constructor, PCLAffinity affinity) {
        super(id, tooltip, constructor, Behavior.SingleTurn, false, true, false, true);
        this.affinity = affinity;

        registerHelper(id, affinity);
    }

    public static PCLElementHelper get(String powerID) {
        return ALL.get(powerID);
    }

    public static PCLElementHelper get(PCLAffinity affinity) {
        return ALL_BY_AFFINITY.getOrDefault(affinity, Lux);
    }

    public static String getPowerAndString(Collection<PCLAffinity> affinities) {
        return PCLCoreStrings.joinWithAnd(EUIUtils.map(affinities, (a) -> {
            return get(a).getTooltip().getTitleOrIcon();
        }));
    }

    public static FuncT1<Boolean, AbstractPower> getPowerFilter(Collection<PCLAffinity> affinities) {
        return (c) -> {
            return affinities.isEmpty() || EUIUtils.any(affinities, (a) -> {
                return get(a).ID.equals(c.ID);
            });
        };
    }

    public static String getPowerOrString(Collection<PCLAffinity> affinities) {
        return PCLCoreStrings.joinWithOr(EUIUtils.map(affinities, (a) -> {
            return get(a).getTooltip().getTitleOrIcon();
        }));
    }

    public static String getPowerString(Collection<PCLAffinity> affinities) {
        return EUIUtils.joinStrings(" ", EUIUtils.map(affinities, (a) -> {
            return get(a).getTooltip().getTitleOrIcon();
        }));
    }

    public static PCLElementHelper random() {
        return GameUtilities.getRandomElement(new ArrayList<>(ALL.values()));
    }

    public static Collection<PCLPowerHelper> sortedValues() {
        return ALL.values().stream().sorted((a, b) -> StringUtils.compare(a.tooltip.title, b.tooltip.title)).collect(Collectors.toList());
    }

    public AbstractPower create(AbstractCreature owner, AbstractCreature source, int amount) {
        return constructorT3.invoke(owner, source, amount);
    }

    protected void registerHelper(String powerID, PCLAffinity affinity) {
        super.registerHelper(powerID);
        ALL.putIfAbsent(powerID, this);
        ALL_BY_AFFINITY.putIfAbsent(affinity, this);
    }
}
