package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class YusukeKitagawa extends PCLCard
{
    public static final PCLCardData DATA = register(YusukeKitagawa.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing)
            .setDamage(3, 0)
            .setPriority(1)
            .setHp(7, 2)
            .setAffinities(PCLAffinity.Blue)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public YusukeKitagawa()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(3),
                PMove.obtain(AnnTakamaki_Carmen.DATA));
    }
}
