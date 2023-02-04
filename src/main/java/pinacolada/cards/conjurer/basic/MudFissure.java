package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PTrait;

@VisibleCard
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
        addDamageMove(PCLAttackVFX.EARTH).setChain(PCond.checkPower(PCLCardTarget.Single, 1).edit(f -> f.setDebuff(true)), PTrait.damageMultiplier(100).setUpgrade(50));
    }
}
