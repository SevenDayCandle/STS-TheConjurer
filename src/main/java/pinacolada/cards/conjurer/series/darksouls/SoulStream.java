package pinacolada.cards.conjurer.series.darksouls;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.PCLCardGroupHelper;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class SoulStream extends PCLCard {
    public static final PCLCardData DATA = register(SoulStream.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Immaterial)
            .setDamage(7, 3)
            .setAffinities(PCLAffinity.Blue.make(2), PCLAffinity.Yellow.make())
            .setLoadout(ConjurerPlayerData.darkSouls);

    public SoulStream() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.MGC_W2_ManaFire).setBonus(PMod.perCard(1, PCLCardGroupHelper.ExhaustPile).edit(f -> f.setAffinity(PCLAffinity.Blue)), 3).setUpgrade(1);
        addUseMove(PCond.exhaust(1), PMove.gainTempHP(3));
    }
}
