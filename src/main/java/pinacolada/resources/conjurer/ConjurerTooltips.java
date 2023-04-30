package pinacolada.resources.conjurer;

import extendedui.ui.tooltips.EUITooltip;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.PCLTooltips;
import pinacolada.resources.PGR;

public class ConjurerTooltips extends PCLTooltips {
    public EUITooltip aer = EUITooltip.findByID("Aer");
    public EUITooltip blasted = EUITooltip.findByID("Blasted");
    public EUITooltip charge = EUITooltip.findByID("Charge");
    public EUITooltip combust = EUITooltip.findByID("Combust");
    public EUITooltip corrosion = EUITooltip.findByID("Corrosion");
    public EUITooltip elementalDebuff = EUITooltip.findByID("Elemental Debuff");
    public EUITooltip elementalExposure = EUITooltip.findByID("Elemental Exposure");
    public EUITooltip elementalMastery = EUITooltip.findByID("Elemental Mastery");
    public EUITooltip flow = EUITooltip.findByID("Flow");
    public EUITooltip frostbite = EUITooltip.findByID("Frostbite");
    public EUITooltip gelus = EUITooltip.findByID("Gelus");
    public EUITooltip ignis = EUITooltip.findByID("Ignis");
    public EUITooltip lux = EUITooltip.findByID("Lux");
    public EUITooltip petra = EUITooltip.findByID("Petra");
    public EUITooltip reaction = EUITooltip.findByID("Reaction");
    public EUITooltip redox = EUITooltip.findByID("Redox");

    public EUITooltip getRerollTooltip() {
        return charge;
    }

    @Override
    public void initializeIcons() {
        aer.setIconFromPath(PGR.getPowerImage(AerPower.POWER_ID));
        blasted.setIconFromPath(PGR.getPowerImage(BlastedPower.POWER_ID));
        corrosion.setIconFromPath(PGR.getPowerImage(CorrosionPower.POWER_ID));
        elementalExposure.setIconFromPath(PGR.getPowerImage(ElementalExposurePower.POWER_ID));
        elementalMastery.setIconFromPath(PGR.getPowerImage(ElementalMasteryPower.POWER_ID));
        flow.setIconFromPath(PGR.getPowerImage(FlowPower.POWER_ID));
        frostbite.setIconFromPath(PGR.getPowerImage(FrostbitePower.POWER_ID));
        gelus.setIconFromPath(PGR.getPowerImage(GelusPower.POWER_ID));
        ignis.setIconFromPath(PGR.getPowerImage(IgnisPower.POWER_ID));
        lux.setIconFromPath(PGR.getPowerImage(LuxPower.POWER_ID));
        petra.setIconFromPath(PGR.getPowerImage(PetraPower.POWER_ID));

        flow.formatDescription(FlowPower.PER_STACK);
    }
}
