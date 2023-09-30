package pinacolada.cards.conjurer.series.shinmegamitensei;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class FutabaSakura extends PCLCard {
    public static final PCLCardData DATA = register(FutabaSakura.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Immaterial, PCLCardTarget.RandomEnemy)
            .setDamage(2, 1)
            .setHp(4, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei, true);

    public FutabaSakura() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.PSYCHOKINESIS);
        addGainPower(PTrigger.when(PCond.onCreate(), PMove.applyToEnemies(1).edit(f -> f.setDebuff(true))));
    }
}
