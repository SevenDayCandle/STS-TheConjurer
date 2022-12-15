package pinacolada.cards.conjurer.series.shinmegamitensei;

import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.conjurer.moves.PMove_GainReaction;
import pinacolada.skills.skills.base.modifiers.PMod_PerPower;

public class PyroJack extends PCLCard
{
    public static final PCLCardData DATA = register(PyroJack.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Magical)
            .setDamage(2, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public PyroJack()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(PMove.applyToSingle(3, PCLPowerHelper.Burning));
        addUseMove(new PMod_PerPower(PCLCardTarget.Single, 1, PCLPowerHelper.Chilled), new PMove_GainReaction(2).setUpgrade(1));
    }
}
