package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.fields.PCLCardTarget;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.FlowPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class DanHeng extends PCLCard {
    public static final PCLCardData DATA = register(DanHeng.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.COMMON, PCLAttackType.Piercing)
            .setDamage(4, 1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Green)
            .setLoadout(ConjurerPlayerData.honkai);

    public DanHeng() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_HORIZONTAL).setBonus(PMultiCond.or(PCond.block(PCLCardTarget.Single, 1), PCond.checkPower(PCLCardTarget.None, 5, FlowPower.DATA)), 3, 2);
    }
}
