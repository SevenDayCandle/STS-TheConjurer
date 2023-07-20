package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.localization.UIStrings;
import pinacolada.resources.AbstractStrings;
import pinacolada.resources.PCLResources;

public class ConjurerStrings extends AbstractStrings {
    private final UIStrings combat = getUIStrings("Combat");
    private final UIStrings tutorial = getUIStrings("Tutorial");
    public final String combat_conjurerMeterCost = combat.TEXT[0];
    public final String combat_conjurerMeterBonus = combat.TEXT[1];
    public final String combat_conjurerMeterReact = combat.TEXT[2];
    public final String combat_conjurerMeterNextIntensity = combat.TEXT[3];
    public final String conjurerSimple = tutorial.TEXT[0];
    public final String conjurerTutorial1 = tutorial.TEXT[1];
    public final String conjurerTutorial2 = tutorial.TEXT[2];
    public final String conjurerTutorial3 = tutorial.TEXT[3];
    public final String conjurerTutorial4 = tutorial.TEXT[4];
    public final String conjurerInteractive1 = tutorial.TEXT[5];
    public final String conjurerInteractive2 = tutorial.TEXT[6];
    public final String conjurerInteractive3 = tutorial.TEXT[7];
    public final String conjurerInteractive4 = tutorial.TEXT[8];
    public final String conjurerSummonInteractive1 = tutorial.TEXT[9];
    public final String conjurerSummonInteractive2 = tutorial.TEXT[10];
    public final String conjurerSummonInteractive3 = tutorial.TEXT[11];
    public final String conjurerSummonInteractive4 = tutorial.TEXT[12];

    public ConjurerStrings(PCLResources<?, ?, ?, ?> resources) {
        super(resources);
    }
}
