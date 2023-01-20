package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Ose extends PCLCard
{
    public static final PCLCardData DATA = register(Ose.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON)
            .setDamage(5, 1)
            .setPriority(1)
            .setHp(9, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Ose()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HEAVY);
        addUseMove(PCond.onWithdraw(), PMove.apply(PCLCardTarget.RandomAlly, 2, PCLPowerHelper.Strength).setUpgrade(1));
    }
}
