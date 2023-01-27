package pinacolada.cards.conjurer.series.genshinimpact;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class KazuhaKaedehara extends PCLCard
{
    public static final PCLCardData DATA = register(KazuhaKaedehara.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.genshinImpact);

    public KazuhaKaedehara()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HORIZONTAL);
        addUseMove(PCond.cooldown(1), PMove.draw(1, PCLAffinity.Green));
    }
}
