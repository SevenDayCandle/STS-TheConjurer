package pinacolada;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import pinacolada.resources.PGR;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.resources.conjurer.ConjurerTooltips;

@SpireInitializer
public class TheConjurer
{
    public static void initialize()
    {
        PGR.registerResource(ConjurerResources.conjurer);
    }
}