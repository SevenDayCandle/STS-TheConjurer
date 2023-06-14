package pinacolada.resources.conjurer;

import extendedui.ui.tooltips.EUIKeywordTooltip;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.PCLTooltips;
import pinacolada.resources.PGR;

public class ConjurerTooltips extends PCLTooltips {
    public EUIKeywordTooltip aer = EUIKeywordTooltip.findByID("Aer");
    public EUIKeywordTooltip blasted = EUIKeywordTooltip.findByID("Blasted");
    public EUIKeywordTooltip charge = EUIKeywordTooltip.findByID("Charge");
    public EUIKeywordTooltip corrosion = EUIKeywordTooltip.findByID("Corrosion");
    public EUIKeywordTooltip elementalDebuff = EUIKeywordTooltip.findByID("Elemental Debuff");
    public EUIKeywordTooltip elementalExposure = EUIKeywordTooltip.findByID("Elemental Exposure");
    public EUIKeywordTooltip elementalMastery = EUIKeywordTooltip.findByID("Elemental Mastery");
    public EUIKeywordTooltip flow = EUIKeywordTooltip.findByID("Flow");
    public EUIKeywordTooltip frostbite = EUIKeywordTooltip.findByID("Frostbite");
    public EUIKeywordTooltip gelus = EUIKeywordTooltip.findByID("Gelus");
    public EUIKeywordTooltip ignis = EUIKeywordTooltip.findByID("Ignis");
    public EUIKeywordTooltip lux = EUIKeywordTooltip.findByID("Lux");
    public EUIKeywordTooltip matter = EUIKeywordTooltip.findByID("Matter");
    public EUIKeywordTooltip petra = EUIKeywordTooltip.findByID("Petra");
    public EUIKeywordTooltip reaction = EUIKeywordTooltip.findByID("Reaction");

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
        frostbite.formatDescription((int) FrostbitePower.POTENCY);
    }
}
