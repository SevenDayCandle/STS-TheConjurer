package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.*;
import pinacolada.cards.base.fields.PCLCardTag;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

public class TectonicQuake extends PCLCard
{
    public static final PCLCardData DATA = register(TectonicQuake.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(25, 4)
            .setAffinities(2, PCLAffinity.Orange)
            .setCore();

    public TectonicQuake()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove();
        addUseMove(PMove.applyToEnemies(3, PCLElementHelper.Stoned, PCLPowerHelper.Weak));
    }
}
