package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class Albedo extends PCLCard
{
    public static final PCLCardData DATA = register(Albedo.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Magical)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(8, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public Albedo()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.EARTH);
        addUseMove(PCond.cooldown(0), PMove.scout(1));
    }
}
