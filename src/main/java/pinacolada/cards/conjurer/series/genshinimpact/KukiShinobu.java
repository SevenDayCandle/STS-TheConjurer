package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class KukiShinobu extends PCLCard
{
    public static final PCLCardData DATA = register(KukiShinobu.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(2, 0, 2)
            .setPriority(1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KukiShinobu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.DAGGER);
        addUseMove(PCond.cooldown(0), PCond.discardRandom(1), PMove.applyToRandom(5, PCLPowerHelper.Poison).setUpgrade(2));
    }
}
