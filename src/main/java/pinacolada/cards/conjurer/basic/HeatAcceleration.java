package pinacolada.cards.conjurer.basic;


import pinacolada.cards.base.PCLAffinity;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.effects.AttackEffects;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.base.traits.PTrait_Damage;

public class HeatAcceleration extends PCLCard
{
    public static final PCLCardData DATA = register(HeatAcceleration.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE)
            .setDamage(5, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Green)
            .setCore();

    public HeatAcceleration()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.FIRE);
        addUseMove(PMod.perCreatureWith(1, PCLElementHelper.Burned, PCLElementHelper.Flowed), new PTrait_Damage(3).setUpgrade(1));
        addUseMove(PMove.gain(2, PCLPowerHelper.NextTurnDraw));
    }
}
