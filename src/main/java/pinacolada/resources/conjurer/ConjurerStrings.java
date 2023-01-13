package pinacolada.resources.conjurer;

import com.megacrit.cardcrawl.localization.UIStrings;
import pinacolada.resources.PCLResources;
import pinacolada.resources.PCLStrings;

public class ConjurerStrings extends PCLStrings
{
    public ConjurerStrings(PCLResources<?,?,?> resources)
    {
        super(resources);
    }

    public class Combat
    {
        private final UIStrings strings = getUIStrings("Combat");

        public final String conjurerMeterDebuff = strings.TEXT[18];
        public final String conjurerMeterCost = strings.TEXT[19];
        public final String conjurerMeterDamage = strings.TEXT[20];
        public final String conjurerMeterSwitch = strings.TEXT[21];
        public final String conjurerMeterCombust = strings.TEXT[22];
        public final String conjurerMeterRedox = strings.TEXT[23];
        public final String conjurerMeterNextIntensity = strings.TEXT[24];
    }

    public class Tutorial
    {
        private final UIStrings strings = getUIStrings("Tutorial");

        public final String conjurerSimple = strings.TEXT[0];
        public final String conjurerTutorial1 = strings.TEXT[1];
        public final String conjurerTutorial2 = strings.TEXT[2];
        public final String conjurerTutorial3 = strings.TEXT[3];
    }
}
