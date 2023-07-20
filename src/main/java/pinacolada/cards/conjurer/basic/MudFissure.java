package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;

@VisibleCard
public class MudFissure extends PCLCard {
    public static final PCLCardData DATA = register(MudFissure.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON)
            .setDamage(5, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setCore();

    public MudFissure() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH).setBonus(PCond.checkDistinctPowerSingle( 2).edit(f -> f.setDebuff(true)), 6, 2);
    }
}
