package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMove;

@VisibleCard
public class Ignite extends PCLCard {
    public static final PCLCardData DATA = register(Ignite.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.COMMON, PCLAttackType.Ranged)
            .setDamage(10, 2)
            .setAffinities(PCLAffinity.Red)
            .setCore();

    public Ignite() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE04);
        addUseMove(PMove.applyToSingle(2, IgnisPower.DATA).setUpgrade(1));
    }
}
