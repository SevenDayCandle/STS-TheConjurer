package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class BoulderHeave extends PCLCard {
    public static final PCLCardData DATA = register(BoulderHeave.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(8, 3)
            .setAffinities(2, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.darkSouls);

    public BoulderHeave() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_EarthSpell_LV1_Impact).setBonus(PMod.perCard(PCLCardGroupHelper.Hand).edit(f -> f.setAffinity(PCLAffinity.Orange)), 4);
    }
}
