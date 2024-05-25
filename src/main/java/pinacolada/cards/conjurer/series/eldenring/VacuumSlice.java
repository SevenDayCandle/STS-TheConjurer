package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;

@VisibleCard
public class VacuumSlice extends PCLCard {
    public static final PCLCardData DATA = register(VacuumSlice.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(6, 2)
            .setAffinities(PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public VacuumSlice() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.SWORD12).setBonus(PCond.checkDistinctPowerSingle(2).edit(f -> f.setDebuff(true)), 6, 2);
    }
}
