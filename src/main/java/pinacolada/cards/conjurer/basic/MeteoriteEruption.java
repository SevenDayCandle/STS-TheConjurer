package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.ConjurerEFK;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.conjurer.modifiers.PMod_PerReaction;

@VisibleCard
public class MeteoriteEruption extends PCLCard {
    public static final PCLCardData DATA = register(MeteoriteEruption.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.RARE, PCLAttackType.Normal, PCLCardTarget.AllEnemy)
            .setDamage(8, 1)
            .setAffinities(PCLAffinity.Red, PCLAffinity.Orange)
            .setCore();

    public MeteoriteEruption() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(ConjurerEFK.SPEAR03).setBonus(new PMod_PerReaction(1), 2, 1);
    }
}
