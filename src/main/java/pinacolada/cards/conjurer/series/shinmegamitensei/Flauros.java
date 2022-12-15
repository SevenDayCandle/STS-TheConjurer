package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_HaveDiscarded;

public class Flauros extends PCLCard
{
    public static final PCLCardData DATA = register(Flauros.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON)
            .setDamage(12, 3)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Flauros()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HEAVY);
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Vulnerable));
        addUseMove(new PCond_HaveDiscarded(1).setAffinity(PCLAffinity.Red), PMove.gain(3, PCLPowerHelper.Vigor));
    }
}
