package pinacolada.cards.conjurer.series.eldenring;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.CooledPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PCond;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;
import pinacolada.skills.skills.PMultiCond;

@VisibleCard
public class FlameOfTheFellGod extends PCLCard {
    public static final PCLCardData DATA = register(FlameOfTheFellGod.class, ConjurerResources.conjurer)
            .setAttack(4, CardRarity.RARE, PCLAttackType.Ranged)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(56, 8)
            .setAffinities(2, PCLAffinity.Red)
            .setLoadout(ConjurerPlayerData.eldenRing);

    public FlameOfTheFellGod() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE03).setBonus(PMod.perPowerSingle(CooledPower.DATA), 4,1);
        addUseMove(PMultiCond.or(PCond.onDiscard(), PCond.onReshuffle()), PMove.applyToEveryone(3, IgnisPower.DATA).setUpgrade(1));
    }
}
