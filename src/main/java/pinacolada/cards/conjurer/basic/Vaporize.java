package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.*;
import pinacolada.effects.AttackEffects;
import pinacolada.effects.PCLEffekseerEFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.modifiers.PMod_PerElement;
import pinacolada.skills.skills.base.traits.PTrait_Damage;

@VisibleCard
public class Vaporize extends PCLCard
{
    public static final PCLCardData DATA = register(Vaporize.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(4, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public Vaporize()
    {
        super(DATA);
    }

    public void setup(Object input)
    {
        addDamageMove(AttackEffects.NONE).setDamageEffect(PCLEffekseerEFX.SWORD18);
        addUseMove(new PMod_PerElement(PCLCardTarget.Single, 1, PCLAffinity.Red), new PTrait_Damage(4).setUpgrade(0, 2));
    }
}
