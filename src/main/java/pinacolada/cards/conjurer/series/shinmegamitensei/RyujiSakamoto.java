package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class RyujiSakamoto extends PCLCard
{
    public static final PCLCardData DATA = register(RyujiSakamoto.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON)
            .setDamage(4, 0)
            .setPriority(1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public RyujiSakamoto()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(3),
                PMove.obtain(RyujiSakamoto_CaptainKidd.DATA));
    }
}
