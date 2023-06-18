package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CMod;
import pinacolada.skills.PMove;

@VisibleCard
public class RazorTurbine extends PCLCard {
    public static final PCLCardData DATA = register(RazorTurbine.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.UNCOMMON, PCLAttackType.Ranged, PCLCardTarget.AllEnemy)
            .setDamage(5, 3)
            .setAffinities(2, PCLAffinity.Green)
            .setCore();

    public RazorTurbine() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.WIND02);
        addUseMove(CMod.bonusOnReact(1), PMove.draw(2));
    }
}
