package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.VentusPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class AirCurrent extends PCLCard {
    public static final PCLCardData DATA = register(AirCurrent.class, ConjurerResources.conjurer)
            .setAttack(0, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(5, 2)
            .setAffinities(PCLAffinity.Green)
            .setCore();

    public AirCurrent() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.WIND);
        addUseMove(PMove.applyToSingle(1, VentusPower.DATA));
    }
}
