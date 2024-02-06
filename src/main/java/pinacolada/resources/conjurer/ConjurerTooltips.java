package pinacolada.resources.conjurer;

import extendedui.ui.tooltips.EUIKeywordTooltip;
import pinacolada.powers.conjurer.BlastedPower;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.AbstractTooltips;

public class ConjurerTooltips extends AbstractTooltips {
    public EUIKeywordTooltip aqua = EUIKeywordTooltip.findByID("conjurer:Aqua");
    public EUIKeywordTooltip backLink = EUIKeywordTooltip.findByID("conjurer:Back Link");
    public EUIKeywordTooltip blasted = EUIKeywordTooltip.findByID("conjurer:Blasted");
    public EUIKeywordTooltip cooled = EUIKeywordTooltip.findByID("conjurer:Cooled");
    public EUIKeywordTooltip charge = EUIKeywordTooltip.findByID("conjurer:Charge");
    public EUIKeywordTooltip element = EUIKeywordTooltip.findByID("conjurer:Element");
    public EUIKeywordTooltip elementalMastery = EUIKeywordTooltip.findByID("conjurer:Elemental Mastery");
    public EUIKeywordTooltip flow = EUIKeywordTooltip.findByID("conjurer:Flow");
    public EUIKeywordTooltip forging = EUIKeywordTooltip.findByID("conjurer:Forging");
    public EUIKeywordTooltip frontLink = EUIKeywordTooltip.findByID("conjurer:Front Link");
    public EUIKeywordTooltip ignis = EUIKeywordTooltip.findByID("conjurer:Ignis");
    public EUIKeywordTooltip impedance = EUIKeywordTooltip.findByID("conjurer:Impedance");
    public EUIKeywordTooltip link = EUIKeywordTooltip.findByID("conjurer:Link");
    public EUIKeywordTooltip lux = EUIKeywordTooltip.findByID("conjurer:Lux");
    public EUIKeywordTooltip petra = EUIKeywordTooltip.findByID("conjurer:Petra");
    public EUIKeywordTooltip reaction = EUIKeywordTooltip.findByID("conjurer:Reaction");
    public EUIKeywordTooltip stabilize = EUIKeywordTooltip.findByID("conjurer:Stabilize");
    public EUIKeywordTooltip umbra = EUIKeywordTooltip.findByID("conjurer:Umbra");
    public EUIKeywordTooltip ventus = EUIKeywordTooltip.findByID("conjurer:Ventus");

    @Override
    public void initializeIcons() {
        blasted.formatDescription(BlastedPower.DECAY);
        cooled.formatDescription(CooledPower.POTENCY);
        flow.formatDescription(FlowPower.PER_STACK);
        forging.formatDescription(ForgingPower.PER_STACK);
    }
}
