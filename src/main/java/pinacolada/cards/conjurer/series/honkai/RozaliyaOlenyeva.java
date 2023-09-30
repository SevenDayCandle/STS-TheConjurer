package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.PCLEnum;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;

@VisibleCard
public class RozaliyaOlenyeva extends PCLCard {
    public static final PCLCardData DATA = register(RozaliyaOlenyeva.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Normal, PCLCardTarget.RandomEnemy)
            .setDamage(4, 1)
            .setHp(5, 2)
            .setAffinities(PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.honkai);

    public RozaliyaOlenyeva() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_HEAVY).setBonus(PMod.perCard(1, PCLCardGroupHelper.Hand).edit(f -> f.setType(PCLEnum.CardType.SUMMON)), 1);
    }
}
