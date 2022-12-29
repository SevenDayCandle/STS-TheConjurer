package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardTarget;
import pinacolada.effects.AttackEffects;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;

public class MudFissure extends PCLCard
{
    public static final PCLCardData DATA = register(MudFissure.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(5, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setCore();

    public MudFissure()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.CLAW);
        addUseMove(PCond.checkPower(PCLCardTarget.Single, 1), PTrait.hasDamageMultiplier(100).setUpgrade(50));
    }
}