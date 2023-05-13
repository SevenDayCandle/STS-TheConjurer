package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.VFX;
import pinacolada.powers.PCLPowerHelper;
import pinacolada.powers.conjurer.PCLElementHelper;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PBranchCond;

@VisibleCard
public class SkyBurial extends PCLCard {
    public static final PCLCardData DATA = register(SkyBurial.class, ConjurerResources.conjurer)
            .setAttack(1, CardRarity.RARE, PCLAttackType.Normal)
            .setDamage(5, 1, 3)
            .setAffinities(1, PCLAffinity.Green)
            .setCore();

    public SkyBurial() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove().setDamageEffect((s, m) -> VFX.razorWind(s.hb).duration);
        addUseMove(PBranchCond.branch(PCond.discardRandom(2), PMove.applyToSingle(1, PCLPowerHelper.Vulnerable, PCLElementHelper.Aer), PMove.selfExhaust()));
    }
}
