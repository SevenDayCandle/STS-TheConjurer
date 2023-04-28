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
public class Teddie extends PCLCard {
    public static final PCLCardData DATA = register(Teddie.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged, PCLCardTarget.RandomEnemy)
            .setDamage(2, 1)
            .setHp(7, 2)
            .setAffinities(1, PCLAffinity.Star)
            .setLoadout(ConjurerPlayerData.shinMegamiTensei, true);

    public Teddie() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SPARK);
        addGainPower(PTrigger.interactable(
                PCond.payEnergy(1),
                PMove.gainPlayer(2))
        );
    }
}
