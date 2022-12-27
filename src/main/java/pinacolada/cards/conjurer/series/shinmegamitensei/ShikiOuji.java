package pinacolada.cards.conjurer.series.shinmegamitensei;


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
import pinacolada.skills.skills.PMultiCond;

public class ShikiOuji extends PCLCard
{
    public static final PCLCardData DATA = register(ShikiOuji.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE)
            .setDamage(1, 1, 2)
            .setPriority(1)
            .setHp(10, 2)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public ShikiOuji()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.SLASH_HEAVY);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onWithdraw()), PMove.apply(PCLCardTarget.AllAlly, 2, PCLPowerHelper.Thorns).setUpgrade(1));
    }
}
