package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLAttackType;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Aitvaras extends PCLCard
{
    public static final PCLCardData DATA = register(Aitvaras.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(4, 1)
            .setPriority(1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Aitvaras()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(PCond.cooldown(1), CMod.perReaction(3).setExtra(10, 2), PMove.applyToSingle(1, PCLElementHelper.Ignis));
    }
}
