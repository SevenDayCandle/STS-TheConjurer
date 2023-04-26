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
import pinacolada.skills.CMove;
import pinacolada.skills.PCond;

@VisibleCard
public class Chironnupu extends PCLCard
{
    public static final PCLCardData DATA = register(Chironnupu.class, ConjurerResources.conjurer)
            .setSummon(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(1, 0, 2)
            .setHp(3, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Yellow)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Chironnupu()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PCond.isAttacking(PCLCardTarget.Single),
                CMove.gainReaction(7).setUpgrade(3));
    }
}
