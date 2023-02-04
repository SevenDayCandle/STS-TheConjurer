package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
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
        addDamageMove(PCLAttackVFX.SLASH_HEAVY);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onWithdraw()), PMove.applyToAllies( 2, PCLPowerHelper.Thorns).setUpgrade(1));
    }
}
