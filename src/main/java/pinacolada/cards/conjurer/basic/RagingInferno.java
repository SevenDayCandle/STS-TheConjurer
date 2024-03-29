package pinacolada.cards.conjurer.basic;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.cards.base.tags.PCLCardTag;
import pinacolada.effects.EffekseerEFK;
import pinacolada.powers.conjurer.AquaPower;
import pinacolada.powers.conjurer.IgnisPower;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.PMod;
import pinacolada.skills.PMove;

@VisibleCard
public class RagingInferno extends PCLCard {
    public static final PCLCardData DATA = register(RagingInferno.class, ConjurerResources.conjurer)
            .setAttack(3, CardRarity.RARE, PCLAttackType.Normal)
            .setTags(PCLCardTag.Exhaust)
            .setDamage(33, 5)
            .setAffinities(2, PCLAffinity.Red)
            .setCore();

    public RagingInferno() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(EffekseerEFK.FIRE15).setBonus(PMod.perPowerSingle(AquaPower.DATA), 3);
        addUseMove(PMove.applyToSingle(3, IgnisPower.DATA).setUpgrade(1));
    }
}
