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
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class RaidenMei extends PCLCard {
    public static final PCLCardData DATA = register(RaidenMei.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Piercing)
            .setDamage(2, 2)
            .setHp(5, 0)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Purple)
            .setLoadout(ConjurerPlayerData.honkai);

    public RaidenMei() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.LIGHTNING).setBonus(PMod.perDistinctPower(PCLCardTarget.Single,1), 4, 1);
        addUseMove(PCond.onDeath(), PMove.draw(2));
    }
}
