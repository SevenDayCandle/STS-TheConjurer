package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class HaruOkumura extends PCLCard
{
    public static final PCLCardData DATA = register(HaruOkumura.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Orange, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public HaruOkumura()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(2),
                PMove.create(HaruOkumura_Milady.DATA.ID));
    }
}
