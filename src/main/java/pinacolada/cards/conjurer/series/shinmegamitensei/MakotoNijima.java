package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class MakotoNijima extends PCLCard
{
    public static final PCLCardData DATA = register(MakotoNijima.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(3, 1)
            .setPriority(1)
            .setHp(8, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public MakotoNijima()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.cooldown(3),
                PMove.create(MakotoNijima_Johanna.DATA.ID));
    }
}
