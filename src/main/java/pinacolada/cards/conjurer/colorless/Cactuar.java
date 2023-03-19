package pinacolada.cards.conjurer.colorless;


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
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class Cactuar extends PCLCard
{
    public static final PCLCardData DATA = register(Cactuar.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(1, 1, 2)
            .setPriority(1)
            .setHp(4, 0)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei);

    public Cactuar()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(PCLAttackVFX.DAGGER);
        addUseMove(PMultiCond.or(PCond.onSummon(), PCond.onWithdraw()), PMove.apply( PCLCardTarget.None, 1, PCLPowerHelper.Thorns).setUpgrade(1));
    }
}
