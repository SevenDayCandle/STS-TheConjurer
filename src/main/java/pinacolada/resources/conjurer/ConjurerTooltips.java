package pinacolada.resources.conjurer;

import extendedui.ui.tooltips.EUITooltip;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.PCLTooltips;
import pinacolada.resources.PGR;

public class ConjurerTooltips extends PCLTooltips
{
    public EUITooltip burning = EUITooltip.findByID("Burning");
    public EUITooltip combust = EUITooltip.findByID("Combust");
    public EUITooltip chilled = EUITooltip.findByID("Chilled");
    public EUITooltip corrosion = EUITooltip.findByID("Corrosion");
    public EUITooltip electrified = EUITooltip.findByID("Electrified");
    public EUITooltip elementalDebuff = EUITooltip.findByID("Elemental Debuff");
    public EUITooltip elementalExposure = EUITooltip.findByID("Elemental Exposure");
    public EUITooltip elementalMastery = EUITooltip.findByID("Elemental Mastery");
    public EUITooltip flowed = EUITooltip.findByID("Flowed");
    public EUITooltip frozen = EUITooltip.findByID("Frozen");
    public EUITooltip morph = EUITooltip.findByID("Morph");
    public EUITooltip reaction = EUITooltip.findByID("Reaction");
    public EUITooltip redox = EUITooltip.findByID("Redox");
    public EUITooltip stoned = EUITooltip.findByID("Stoned");

    @Override
    public void initializeIcons()
    {
        burning.setIconFromPath(PGR.getPowerImage(BurningPower.POWER_ID));
        chilled.setIconFromPath(PGR.getPowerImage(ChilledPower.POWER_ID));
        corrosion.setIconFromPath(PGR.getPowerImage(CorrosionPower.POWER_ID));
        electrified.setIconFromPath(PGR.getPowerImage(ElectrifiedPower.POWER_ID));
        elementalExposure.setIconFromPath(PGR.getPowerImage(ElementalExposurePower.POWER_ID));
        elementalMastery.setIconFromPath(PGR.getPowerImage(ElementalMasteryPower.POWER_ID));
        flowed.setIconFromPath(PGR.getPowerImage(FlowedPower.POWER_ID));
        frozen.setIconFromPath(PGR.getPowerImage(FrozenPower.POWER_ID));
        stoned.setIconFromPath(PGR.getPowerImage(StonedPower.POWER_ID));
    }

    public EUITooltip getLevelTooltip(PCLAffinity affinity)
    {
        switch (affinity)
        {
            case Red:
                return burning;
            case Green:
                return flowed;
            case Blue:
                return chilled;
            case Orange:
                return stoned;
            case Light:
                return electrified;
            case Dark:
                return corrosion;
            case Silver:
            case Star:
            case General:
            case Unknown:
            default:
                return reaction;
        }
    }

    public EUITooltip getRerollTooltip()
    {
        return morph;
    }
}
