package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.PCLPowerData;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class Mobius extends PCLCard {
    public static final PCLCardData DATA = register(Mobius.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Piercing, PCLCardTarget.RandomEnemy)
            .setDamage(3, array(2, 0))
            .setHp(4, 0)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green, PCLAffinity.Silver)
            .setLoadout(ConjurerPlayerData.honkai);

    public Mobius() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.POISON);
        addUseMove(CCond.linkFront(PCLAffinity.Green, PCLAffinity.Blue), PMultiSkill.join(PMove.takeDamage(2).setUpgrade(0, 2), PMove.applyToSingle(3, PCLPowerData.Poison).setUpgrade(0, 2)));
    }
}
