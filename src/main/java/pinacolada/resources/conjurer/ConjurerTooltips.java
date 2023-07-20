package pinacolada.resources.conjurer;

import extendedui.ui.tooltips.EUIKeywordTooltip;
import pinacolada.powers.conjurer.*;
import pinacolada.resources.AbstractTooltips;
import pinacolada.resources.PGR;

public class ConjurerTooltips extends AbstractTooltips {
    public EUIKeywordTooltip aqua = EUIKeywordTooltip.findByID("conjurer:Aqua");
    public EUIKeywordTooltip blasted = EUIKeywordTooltip.findByID("conjurer:Blasted");
    public EUIKeywordTooltip charge = EUIKeywordTooltip.findByID("conjurer:Charge");
    public EUIKeywordTooltip corrosion = EUIKeywordTooltip.findByID("conjurer:Corrosion");
    public EUIKeywordTooltip elementalDebuff = EUIKeywordTooltip.findByID("conjurer:Elemental Debuff");
    public EUIKeywordTooltip elementalExposure = EUIKeywordTooltip.findByID("conjurer:Elemental Exposure");
    public EUIKeywordTooltip elementalMastery = EUIKeywordTooltip.findByID("conjurer:Elemental Mastery");
    public EUIKeywordTooltip flow = EUIKeywordTooltip.findByID("conjurer:Flow");
    public EUIKeywordTooltip frostbite = EUIKeywordTooltip.findByID("conjurer:Frostbite");
    public EUIKeywordTooltip ignis = EUIKeywordTooltip.findByID("conjurer:Ignis");
    public EUIKeywordTooltip lux = EUIKeywordTooltip.findByID("conjurer:Lux");
    public EUIKeywordTooltip matter = EUIKeywordTooltip.findByID("conjurer:Matter");
    public EUIKeywordTooltip petra = EUIKeywordTooltip.findByID("conjurer:Petra");
    public EUIKeywordTooltip reaction = EUIKeywordTooltip.findByID("conjurer:Reaction");
    public EUIKeywordTooltip ventus = EUIKeywordTooltip.findByID("conjurer:Ventus");

    @Override
    public void initializeIcons() {
        aqua.setIconFromPath(PGR.getPowerImage(AquaPower.POWER_ID));
        blasted.setIconFromPath(PGR.getPowerImage(BlastedPower.POWER_ID));
        corrosion.setIconFromPath(PGR.getPowerImage(CorrosionPower.POWER_ID));
        elementalExposure.setIconFromPath(PGR.getPowerImage(ElementalExposurePower.POWER_ID));
        elementalMastery.setIconFromPath(PGR.getPowerImage(ElementalMasteryPower.POWER_ID));
        flow.setIconFromPath(PGR.getPowerImage(FlowPower.POWER_ID));
        frostbite.setIconFromPath(PGR.getPowerImage(FrostbitePower.POWER_ID));
        ignis.setIconFromPath(PGR.getPowerImage(IgnisPower.POWER_ID));
        lux.setIconFromPath(PGR.getPowerImage(LuxPower.POWER_ID));
        petra.setIconFromPath(PGR.getPowerImage(PetraPower.POWER_ID));
        ventus.setIconFromPath(PGR.getPowerImage(VentusPower.POWER_ID));

        flow.formatDescription(FlowPower.PER_STACK);
        frostbite.formatDescription((int) FrostbitePower.POTENCY);
    }
}
