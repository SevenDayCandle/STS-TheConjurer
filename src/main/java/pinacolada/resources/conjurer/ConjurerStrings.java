package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.localization.UIStrings;
import pinacolada.resources.PCLResources;
import pinacolada.resources.PCLStrings;

public class ConjurerStrings extends PCLStrings
{
    private final UIStrings strings = getUIStrings("Tutorial");

    public ConjurerStrings(PCLResources<?,?,?> resources)
    {
        super(resources);
    }

    public final String conjurerSimple = strings.TEXT[0];
    public final String conjurerTutorial1 = strings.TEXT[1];
    public final String conjurerTutorial2 = strings.TEXT[2];
    public final String conjurerTutorial3 = strings.TEXT[3];
}
