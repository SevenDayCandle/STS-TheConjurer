package pinacolada;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;

@SpireInitializer
public class TheConjurer {
    public static void initialize() {
        PGR.registerResource(ConjurerResources.conjurer);
    }
}