package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;

@VisibleCard
public class Flauros extends PCLCard
{
    public static final PCLCardData DATA = register(Flauros.class, ConjurerResources.conjurer)
            .setSummon(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(5, 1)
            .setPriority(1)
            .setHp(8, 2)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Flauros()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.CLAW);
        addUseMove(PCond.onWithdraw(), PMove.applyToEnemies(3, PCLPowerHelper.Vulnerable));
    }
}
