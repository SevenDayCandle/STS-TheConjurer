package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.special.moves.PMove_ExhaustAlly;

@VisibleCard
public class RyujiSakamoto_CaptainKidd extends PCLCard
{
    public static final PCLCardData DATA = register(RyujiSakamoto_CaptainKidd.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.SPECIAL)
            .setDamage(9, 3)
            .setHp(7, 1)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public RyujiSakamoto_CaptainKidd()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addUseMove(PMove.applyToEnemies(2, PCLPowerHelper.Vulnerable, PCLPowerHelper.Weak), new PMove_ExhaustAlly(PCLCardTarget.Self, 1));
    }
}
