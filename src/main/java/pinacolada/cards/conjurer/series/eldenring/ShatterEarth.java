package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.powers.conjurer.ForgingPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PBranchCond;

@VisibleCard
public class ShatterEarth extends PCLCard {
    public static final PCLCardData DATA = register(ShatterEarth.class, ConjurerResources.conjurer)
            .setAttack(2, CardRarity.UNCOMMON, PCLAttackType.Normal)
            .setDamage(16, 4)
            .setAffinities(PCLAffinity.Blue, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public ShatterEarth() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.EARTH);
        addUseMove(PBranchCond.branch(PCond.payPower(5, ForgingPower.DATA), PMove.create(3, SwiftGlintstoneShard.DATA.ID), PMove.gain(3, ForgingPower.DATA)));
    }
}
