package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class BarbaraPegg extends PCLCard
{
    public static final PCLCardData DATA = register(BarbaraPegg.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical, PCLCardTarget.AllEnemy)
            .setDamage(1, 0)
            .setPriority(1)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Light)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public BarbaraPegg()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.WATER);
        addUseMove(PCond.cooldown(0), PMove.heal(PCLCardTarget.AllAlly, 2).setUpgrade(1));
    }
}
