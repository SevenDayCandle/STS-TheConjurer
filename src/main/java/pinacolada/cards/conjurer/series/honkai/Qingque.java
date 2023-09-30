package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PTrigger;

@VisibleCard
public class Qingque extends PCLCard {
    public static final PCLCardData DATA = register(Qingque.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(4, array(1, 0))
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.honkai);

    public Qingque() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.BLUNT_LIGHT);
        addGainPower((PTrigger) PTrigger.interactable(2, PMove.cycleRandom(1)).setUpgrade(0, 1));
    }
}
