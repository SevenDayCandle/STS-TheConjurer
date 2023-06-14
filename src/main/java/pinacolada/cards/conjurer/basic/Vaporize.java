package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.EffekseerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;

@VisibleCard
public class Vaporize extends PCLCard {
    public static final PCLCardData DATA = register(Vaporize.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(7, 3)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Blue)
            .setCore();

    public Vaporize() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.SWORD18)
                .setBonus(CMod.perElement(PCLCardTarget.Single, 1, PCLAffinity.Red), 1);
    }
}
