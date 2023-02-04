package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;

@VisibleCard
public class Mothman extends PCLCard
{
    public static final PCLCardData DATA = register(Mothman.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(2, 1)
            .setPriority(1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Mothman()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.WIND);
        addUseMove(PCond.cooldown(1), PMod.bonusPerLevel(4, PCLAffinity.Green), CMove.gainReaction(4));
    }
}
