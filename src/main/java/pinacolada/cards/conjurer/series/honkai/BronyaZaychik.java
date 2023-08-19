package pinacolada.cards.conjurer.series.honkai;


import pinacolada.annotations.VisibleCard;
import pinacolada.cards.base.PCLCard;
import pinacolada.cards.base.PCLCardData;
import pinacolada.cards.base.fields.PCLAffinity;
import pinacolada.cards.base.fields.PCLAttackType;
import pinacolada.effects.PCLAttackVFX;
import pinacolada.resources.conjurer.ConjurerPlayerData;
import pinacolada.resources.conjurer.ConjurerResources;
import pinacolada.skills.CCond;
import pinacolada.skills.PTrait;
import pinacolada.skills.skills.PMultiSkill;

@VisibleCard
public class BronyaZaychik extends PCLCard {
    public static final PCLCardData DATA = register(BronyaZaychik.class, ConjurerResources.conjurer)
            .setSummon(1, CardRarity.RARE, PCLAttackType.Ranged)
            .setDamage(4, 1)
            .setHp(5, 1)
            .setAffinities(PCLAffinity.Green, PCLAffinity.Orange)
            .setLoadout(ConjurerPlayerData.honkai);

    public BronyaZaychik() {
        super(DATA);
    }

    public void setup(Object input) {
        addDamageMove(PCLAttackVFX.IRON);
        addUseMove(CCond.linkFront(PCLAffinity.Red, PCLAffinity.Orange), PMultiSkill.join(PTrait.hitCount(1), PTrait.damageMultiplier(-35)));
    }
}
