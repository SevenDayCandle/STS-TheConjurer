package pinacolada.cards.conjurer.series.persona;

import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.conditions.PCond_CycleTo;
import pinacolada.skills.skills.base.moves.PMove_Reshuffle;

public class Morgana extends PCLCard
{
    public static final PCLCardData DATA = register(Morgana.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON)
            .setDamage(1, 1)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.persona);

    public Morgana()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.DAGGER);
        addUseMove(new PMove_Reshuffle(1, PCLCardGroupHelper.DiscardPile).setUpgrade(1));
        addUseMove(new PCond_CycleTo(1), PMove.applyToSingle(2, PCLPowerHelper.Flowed));
    }
}
