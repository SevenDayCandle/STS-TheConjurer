package pinacolada.powers.conjurer;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import extendedui.EUIUtils;
import extendedui.interfaces.delegates.FuncT1;
import extendedui.interfaces.delegates.FuncT3;
import extendedui.interfaces.markers.TooltipProvider;
import extendedui.ui.tooltips.EUITooltip;
import org.apache.commons.lang3.StringUtils;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.pcl.PCLCoreStrings;
import pinacolada.utilities.GameUtilities;

import java.util.*;
import java.util.stream.Collectors;

public class PCLElementHelper implements TooltipProvider
{
    protected static final Map<String, PCLElementHelper> ALL = new HashMap<>();
    protected static final Map<PCLAffinity, PCLElementHelper> ALL_BY_AFFINITY = new HashMap<>();

    public static final PCLElementHelper Burning = new PCLElementHelper(BurningPower.POWER_ID, BurningPower::new, BurningPower.AFFINITY, ConjurerResources.conjurer.tooltips.burning);
    public static final PCLElementHelper Chilled = new PCLElementHelper(ChilledPower.POWER_ID, ChilledPower::new, ChilledPower.AFFINITY, ConjurerResources.conjurer.tooltips.chilled);
    public static final PCLElementHelper Electrified = new PCLElementHelper(ElectrifiedPower.POWER_ID, ElectrifiedPower::new, ElectrifiedPower.AFFINITY, ConjurerResources.conjurer.tooltips.electrified);
    public static final PCLElementHelper Flowed = new PCLElementHelper(FlowedPower.POWER_ID, FlowedPower::new, FlowedPower.AFFINITY, ConjurerResources.conjurer.tooltips.flowed);
    public static final PCLElementHelper Stoned = new PCLElementHelper(StonedPower.POWER_ID, StonedPower::new, StonedPower.AFFINITY, ConjurerResources.conjurer.tooltips.stoned);
    
    public final String ID;
    public final PCLAffinity affinity;
    public final EUITooltip tooltip;
    protected final FuncT3<AbstractPower, AbstractCreature, AbstractCreature, Integer> constructor;

    public PCLElementHelper(String id, FuncT3<AbstractPower, AbstractCreature, AbstractCreature, Integer> constructor, PCLAffinity affinity, EUITooltip tooltip)
    {
        ID = id;
        this.affinity = affinity;
        this.tooltip = tooltip;
        this.constructor = constructor;

        registerHelper(id, affinity);
    }

    @Override
    public List<EUITooltip> getTips()
    {
        return Collections.singletonList(tooltip);
    }

    protected void registerHelper(String powerID, PCLAffinity affinity)
    {
        ALL.putIfAbsent(powerID, this);
        ALL_BY_AFFINITY.putIfAbsent(affinity, this);
    }

    public static PCLElementHelper get(String powerID)
    {
        return ALL.get(powerID);
    }

    public static PCLElementHelper get(PCLAffinity affinity)
    {
        return ALL_BY_AFFINITY.getOrDefault(affinity, Electrified);
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

    public static PCLElementHelper random()
    {
        return GameUtilities.getRandomElement(new ArrayList<>(ALL.values()));
    }

    public static Collection<PCLElementHelper> sortedValues()
    {
        return ALL.values().stream().sorted((a, b) -> StringUtils.compare(a.tooltip.title, b.tooltip.title)).collect(Collectors.toList());
    }

    public AbstractPower create(AbstractCreature owner, AbstractCreature source, int amount)
    {
        return constructor.invoke(owner, source, amount);
    }
}
