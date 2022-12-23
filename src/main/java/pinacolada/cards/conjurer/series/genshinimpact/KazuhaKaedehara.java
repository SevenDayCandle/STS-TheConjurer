package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

public class KazuhaKaedehara extends PCLCard
{
    public static final PCLCardData DATA = register(KazuhaKaedehara.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(4, 0)
            .setPriority(1)
            .setHp(8, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KazuhaKaedehara()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(1), PMove.applyToEnemies(2, PCLElementHelper.Flowed).setUpgrade(1));
    }
}
