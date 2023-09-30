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
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PBranchCond;

@VisibleCard
public class RitaRossweisse extends PCLCard {
    public static final PCLCardData DATA = register(RitaRossweisse.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.UNCOMMON, PCLAttackType.Piercing, PCLCardTarget.Single)
            .setDamage(2, 1)
            .setHp(6, 2)
            .setAffinities(PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.honkai);

    public RitaRossweisse() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.SLASH_VERTICAL);
        addUseMove(PBranchCond.branch(PCond.isAttacking(PCLCardTarget.Single), PMove.applyToSingle(1, PCLPowerData.Weak), PMove.applyToSingle(1, PCLPowerData.Vulnerable)));
    }
}
