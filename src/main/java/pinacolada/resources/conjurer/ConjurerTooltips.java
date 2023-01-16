package pinacolada.resources.conjurer;

import extendedui.ui.tooltips.EUITooltip;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.PCLTooltips;
import pinacolada.resources.PGR;

public class ConjurerTooltips extends PCLTooltips
{
    public EUITooltip burned = EUITooltip.findByID("Ignis");
    public EUITooltip combust = EUITooltip.findByID("Combust");
    public EUITooltip charge = EUITooltip.findByID("Charge");
    public EUITooltip chilled = EUITooltip.findByID("Gelus");
    public EUITooltip corrosion = EUITooltip.findByID("Corrosion");
    public EUITooltip electrified = EUITooltip.findByID("Lux");
    public EUITooltip elementalDebuff = EUITooltip.findByID("Elemental Debuff");
    public EUITooltip elementalExposure = EUITooltip.findByID("Elemental Exposure");
    public EUITooltip elementalMastery = EUITooltip.findByID("Elemental Mastery");
    public EUITooltip flowed = EUITooltip.findByID("Aer");
    public EUITooltip frostbite = EUITooltip.findByID("Frostbite");
    public EUITooltip reaction = EUITooltip.findByID("Reaction");
    public EUITooltip redox = EUITooltip.findByID("Redox");
    public EUITooltip stoned = EUITooltip.findByID("Petra");

    @Override
    public void initializeIcons()
    {
        burned.setIconFromPath(PGR.getPowerImage(IgnisPower.POWER_ID));
        chilled.setIconFromPath(PGR.getPowerImage(GelusPower.POWER_ID));
        corrosion.setIconFromPath(PGR.getPowerImage(CorrosionPower.POWER_ID));
        electrified.setIconFromPath(PGR.getPowerImage(LuxPower.POWER_ID));
        elementalExposure.setIconFromPath(PGR.getPowerImage(ElementalExposurePower.POWER_ID));
        elementalMastery.setIconFromPath(PGR.getPowerImage(ElementalMasteryPower.POWER_ID));
        flowed.setIconFromPath(PGR.getPowerImage(AerPower.POWER_ID));
        frostbite.setIconFromPath(PGR.getPowerImage(FrostbitePower.POWER_ID));
        stoned.setIconFromPath(PGR.getPowerImage(PetraPower.POWER_ID));
    }

    public EUITooltip getLevelTooltip(PCLAffinity affinity)
    {
        switch (affinity)
        {
            case Red:
                return burned;
            case Green:
                return flowed;
            case Blue:
                return chilled;
            case Orange:
                return stoned;
            case Yellow:
                return electrified;
            case Purple:
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
        return charge;
    }
}
